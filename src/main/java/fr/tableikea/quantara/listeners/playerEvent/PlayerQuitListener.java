package fr.tableikea.quantara.listeners.playerEvent;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.managers.ScoreboardManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        for (Team team : scoreboard.getTeams()) {
            team.removeEntry(player.getName());
        }

        for(Player pl : Bukkit.getOnlinePlayers()){
            ScoreboardManager.updateScoreboard(pl);
        }

        // Message de déconnexion (optionnel)
        event.quitMessage(Component.text(
                Main.getInstance().getConfig().getString("messages.prefix", "§7[§bQuantara§7] ") +
                        "§e" + player.getName() + " §7a quitter le serveur."
        ));
    }

}
