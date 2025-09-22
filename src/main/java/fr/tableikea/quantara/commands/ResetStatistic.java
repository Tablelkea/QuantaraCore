package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ResetStatistic implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        FileConfiguration messagesConfig = Main.getInstance().getMessagesConfig();

        if(!sender.hasPermission("admin.use")) {
            sender.sendMessage(Component.text(messagesConfig.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
            return true;
        }

        if(sender instanceof Player player){

            Statistic stat = Statistic.valueOf(args[0]);

            player.setStatistic(stat, 0);
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if(args.length == 1){
            List<String> stats = new ArrayList<>();
            for(Statistic stat : Statistic.values()){
                stats.add(stat.toString());
            }
            return stats;
        }

        return List.of();
    }
}
