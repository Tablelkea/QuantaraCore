package fr.tableikea.quantara.scoreboard;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.models.rank.RankColor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class QScoreboard {

    public static void loadScoreBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        if (scoreboard.getObjective("sidebar") != null) {
            scoreboard.getObjective("sidebar").unregister();
        }

        Objective obj = scoreboard.registerNewObjective("sidebar", Criteria.DUMMY, Component.text("§3§lQuantara"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore("§e ").setScore(8);
        obj.getScore("§9Profil:").setScore(7);
        obj.getScore("    §3Grade: §r" + RankColor.getRankColor(Main.getInstance().getConfig().getString("qprofils." + player.getUniqueId() + ".rank"))).setScore(6);

        int playTimeHours = (player.getStatistic(Statistic.TOTAL_WORLD_TIME) / 20) / 3600;
        obj.getScore("§7    §3Présence: §7" + playTimeHours + "h").setScore(5);

        obj.getScore("§6 ").setScore(4);
        obj.getScore("§dInfos:").setScore(3);
        obj.getScore("§7    §5Connectés: §7" + Bukkit.getOnlinePlayers().size()).setScore(2);
        obj.getScore("§c ").setScore(1);
        obj.getScore("§9mc.quantara.fr").setScore(0);

        Team team = scoreboard.getTeam("default") != null
                ? scoreboard.getTeam("default")
                : scoreboard.registerNewTeam("default");

        if (!team.hasEntry(player.getName())) {
            team.addEntry(player.getName());
        }

        player.setScoreboard(scoreboard);
    }

    public static void updateScoreboard(Player player) {
        Scoreboard board = player.getScoreboard();
        if (board == null) return;

        Objective obj = board.getObjective("sidebar");
        if (obj == null) return;

        int playTimeHours = (player.getStatistic(Statistic.TOTAL_WORLD_TIME) / 20) / 3600;
        obj.getScore("§7    §3Présence: §7" + playTimeHours + "h").setScore(5);

        obj.getScore("§7    §5Connectés: §7" + Bukkit.getOnlinePlayers().size()).setScore(2);
    }

    public static void updateAllScoreboards() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            updateScoreboard(player);
        }
    }

    public static void startScoreboardUpdater() {
        // Mise à jour toutes les 5 minutes = 6000 ticks
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), QScoreboard::updateAllScoreboards, 0L, 6000L);
    }
}
