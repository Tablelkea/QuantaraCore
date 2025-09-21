package fr.tableikea.quantara.listeners;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.models.QPlayer;
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
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Nettoyage des teams (facultatif mais recommandé pour éviter les bugs d'affichage)
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        for (Team team : scoreboard.getTeams()) {
            team.removeEntry(player.getName());
        }

        // Message de déconnexion (optionnel)
        event.quitMessage(Component.text("§c" + player.getName() + " §7a quitté le serveur."));
    }
}
