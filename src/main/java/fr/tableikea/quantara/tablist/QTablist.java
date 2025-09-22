package fr.tableikea.quantara.tablist;

import fr.tableikea.quantara.models.QPlayer;
import fr.tableikea.quantara.models.rank.Rank;
import fr.tableikea.quantara.models.rank.RankColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class QTablist {

    public static void loadTabList() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        for (Player player : Bukkit.getOnlinePlayers()) {
            QPlayer qPlayer = QPlayer.getProfilMap().get(player.getUniqueId());
            if (qPlayer == null) continue;

            Rank rank = qPlayer.getRank();

            // Créer une team par rang, avec un nom triable
            String teamName = switch (rank) {
                case ADMIN -> "a_admin";
                case MODERATEUR -> "b_modo";
                case HELPER -> "c_helper";
                case VIP -> "d_vip";
                default -> "z_joueur";
            };

            // Récupérer ou créer l'équipe
            Team team = scoreboard.getTeam(teamName);
            if (team == null) {
                team = scoreboard.registerNewTeam(teamName);
                team.prefix(RankColor.getDefaultRankColor(rank.toString()));
                team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
            }

            // Ajouter le joueur à la team
            if (!team.hasEntry(player.getName())) {
                team.addEntry(player.getName());
            }

            // Nom dans la tablist

            Component displayName = RankColor.getChatRankColor(rank.toString(), player.getName());

            player.displayName();
            player.playerListName(displayName);

            // Pour Paper, on peut aussi ajouter un ordre
            int order = switch (rank) {
                case ADMIN -> 20;
                case MODERATEUR -> 15;
                case HELPER -> 10;
                case VIP -> 5;
                default -> 0;
            };
            player.setPlayerListOrder(order);

            // Header/Footer
            player.sendPlayerListHeaderAndFooter(
                    Component.text("§8§m───────────────\n" +
                            "§9§lQuantara §7• §fServeur Minecraft\n" +
                            "§8\n" +
                            "§e" + Bukkit.getOnlinePlayers().size() + " §7joueur" + (Bukkit.getOnlinePlayers().size() > 1 ? "s" : "") + " connecté" + (Bukkit.getOnlinePlayers().size() > 1 ? "s" : "") + "\n" +
                            "§8§m───────────────"),
                    Component.text("§8§m───────────────\n" +
                            "§bSite : §7mc.quantara.fr\n" +
                            "§8§m───────────────")
            );
        }
    }
}
