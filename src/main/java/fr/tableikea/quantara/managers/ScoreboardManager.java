package fr.tableikea.quantara.managers;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.managers.rank.RankColorManager;
import fr.tableikea.quantara.models.CreateScoreboard;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardManager {

    public static void loadScoreBoard(Player player) {
        player.setScoreboard(CreateScoreboard.get(player));
    }

    public static void updateScoreboard(Player player) {
        Scoreboard board = player.getScoreboard();

        Objective obj = board.getObjective("sidebar");
        if (obj == null) return;

        board.getTeam("online_players").suffix(Component.text(Bukkit.getOnlinePlayers().size()));

        int playTimeHours = (player.getStatistic(Statistic.TOTAL_WORLD_TIME) / 20) / 3600;
        board.getTeam("playtime").suffix(Component.text(playTimeHours + "h"));

        board.getTeam("rank").suffix(RankColorManager.getPlayerPrefixInScoreboard(player.getUniqueId()));

    }

    public static void updateAllScoreboards() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            updateScoreboard(player);
        }
    }

    public static void startScoreboardUpdater() {
        // Mise à jour toutes les 5 minutes = 6000 ticks
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), ScoreboardManager::updateAllScoreboards, 0L, 6000L);
    }
}
