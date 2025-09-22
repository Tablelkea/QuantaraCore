package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.managers.ScoreboardManager;
import fr.tableikea.quantara.managers.TablistManager;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class GlobalUpdate implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        ScoreboardManager.updateAllScoreboards();
        TablistManager.loadTablist();
        TablistManager.loadOrder();

        sender.sendMessage(Component.text("§aServeur mis à jour avec succès."));

        return true;
    }
}
