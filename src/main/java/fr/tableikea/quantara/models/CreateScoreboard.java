package fr.tableikea.quantara.models;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.managers.rank.RankColorManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.UUID;

public class CreateScoreboard {

    public static Scoreboard get(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        FileConfiguration config = Main.getInstance().getConfig();
        UUID playerUUID = player.getUniqueId();
        Scoreboard scoreboard = manager.getNewScoreboard();

        if(config.get("qprofils."+playerUUID) == null){
            new QProfile(playerUUID);
            System.out.println("Profil créer pour " + player.getName());
        }

        String path = "qprofils." + playerUUID + ".";

        Objective obj = scoreboard.registerNewObjective("sidebar", Criteria.DUMMY, Component.text("§3§lQuantara"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore("§e ").setScore(8);
        obj.getScore("§9Profil:").setScore(7);
        Team rank = obj.getScoreboard().registerNewTeam("rank");
        rank.addEntry("    §3Grade: §r");
        rank.suffix(RankColorManager.getPlayerPrefixInScoreboard(playerUUID));
        obj.getScore("    §3Grade: §r").setScore(6);

        int playTimeHours = (player.getStatistic(Statistic.TOTAL_WORLD_TIME) / 20) / 3600;
        Team playtime = obj.getScoreboard().registerNewTeam("playtime");
        playtime.addEntry("§7    §3Présence: §7");
        playtime.suffix(Component.text("PLAYTIME"));
        obj.getScore("§7    §3Présence: §7").setScore(5);

        obj.getScore("§6 ").setScore(4);
        obj.getScore("§dInfos:").setScore(3);
        Team online_players = obj.getScoreboard().registerNewTeam("online_players");
        online_players.addEntry("§7    §5Connectés: §7");
        online_players.suffix(Component.text("CONNECTES"));
        obj.getScore("§7    §5Connectés: §7").setScore(2);
        obj.getScore("§c ").setScore(1);
        obj.getScore("§9mc.quantara.fr").setScore(0);
        return scoreboard;
    }

}
