package fr.tableikea.quantara.listeners;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.models.QPlayer;
import fr.tableikea.quantara.models.rank.Rank;
import fr.tableikea.quantara.scoreboard.QScoreboard;
import fr.tableikea.quantara.tablist.QTablist;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration config = Main.getInstance().getConfig();
        Player player = event.getPlayer();

        // Message de connexion personnalisé
        event.joinMessage(Component.text(
                config.getString("messages.prefix", "§7[§bQuantara§7] ") +
                        "§e" + player.getName() + " §7a rejoint le serveur."
        ));

        // Chargement du profil si besoin
        if ((Main.debugMode && !QPlayer.getProfilMap().containsKey(player.getUniqueId()))
                || config.getBoolean("settings.duplicate_profil")) {
            new QPlayer(player);
            player.sendMessage(Component.text(
                    config.getString("messages.console_prefix", "[Console] ") +
                            "Profil créé avec succès, veuillez vérifier le fichier '§cconfig.yml§7'")
            );
        }

        // Charger scoreboard et tablist
        QScoreboard.loadScoreBoard(player);
        QTablist.loadTabList(); // Cette méthode met à jour pour tous les joueurs

        for(Player pl : Bukkit.getOnlinePlayers()){
            QScoreboard.updateScoreboard(pl);


            Rank rank = Rank.valueOf(Main.getInstance().getConfig().getString("qprofils."+pl.getUniqueId()+".rank"));
            switch (rank){
                case ADMIN ->  {
                    PermissionAttachment perm = pl.addAttachment(Main.getInstance());
                    perm.setPermission("admin.use", true);
                }case MODERATEUR -> {
                    PermissionAttachment perm = pl.addAttachment(Main.getInstance());
                    perm.setPermission("moderateur.use", true);
                }case HELPER -> {
                    PermissionAttachment perm = pl.addAttachment(Main.getInstance());
                    perm.setPermission("helper.use", true);
                }case VIP -> {
                    PermissionAttachment perm = pl.addAttachment(Main.getInstance());
                    perm.setPermission("vip.use", true);
                }case JOUEUR ->{
                    PermissionAttachment perm = pl.addAttachment(Main.getInstance());
                    perm.setPermission("player.use", true);
                }
            }

            pl.recalculatePermissions();
            pl.updateCommands(); // Paper uniquement
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Nettoyage des teams (facultatif mais recommandé pour éviter les bugs d'affichage)
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        for (Team team : scoreboard.getTeams()) {
            team.removeEntry(player.getName());
        }

        for(Player pl : Bukkit.getOnlinePlayers()){
            QScoreboard.updateScoreboard(pl);
        }

        // Message de déconnexion (optionnel)
        event.quitMessage(Component.text(
                Main.getInstance().getConfig().getString("messages.prefix", "§7[§bQuantara§7] ") +
                        "§e" + player.getName() + " §7a quitter le serveur."
        ));
    }
}
