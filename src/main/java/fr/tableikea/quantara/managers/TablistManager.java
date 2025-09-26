package fr.tableikea.quantara.managers;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.models.Rank;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TablistManager {

    public static void loadOrder(){

        FileConfiguration config = Main.getInstance().getConfig();

        for(Player player : Bukkit.getOnlinePlayers()){
            UUID playerUUID = player.getUniqueId();
            Component displayName = RankColorManager.getPlayerPrefixInTab(playerUUID);
            String path = "qprofils."+playerUUID+".";

            player.displayName();
            player.playerListName(displayName);

            Rank rank = Rank.valueOf(config.getString(path+"permission"));

            // Pour Paper, on peut aussi ajouter un ordre
            int order = switch (rank) {
                case ADMIN -> 20;
                case MODERATEUR -> 15;
                case HELPER -> 10;
                case VIP -> 5;
                case PLAYER -> 0;
                default -> 0;
            };
            player.setPlayerListOrder(order);
        }
    }

    public static void loadTablist(){
        for(Player player : Bukkit.getOnlinePlayers()){
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
