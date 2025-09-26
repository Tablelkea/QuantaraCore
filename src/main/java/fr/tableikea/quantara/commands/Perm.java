package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.managers.ScoreboardManager;
import fr.tableikea.quantara.managers.TablistManager;
import fr.tableikea.quantara.managers.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Perm implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        Player target = Bukkit.getPlayer(args[0]);
        UUID targetUUID = target.getUniqueId();
        String rank = args[1];

        RankManager.setPlayerRank(targetUUID, rank);
        sender.sendMessage("§cLe grade a été donner avec succès.");
        TablistManager.loadTablist();
        TablistManager.loadOrder();
        ScoreboardManager.updateAllScoreboards();
        for(Player player : Bukkit.getOnlinePlayers()){
            player.recalculatePermissions();
            player.updateCommands();
        }

        return true;
    }
}
