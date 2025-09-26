package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.managers.TablistManager;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class UpdateTablist implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        TablistManager.loadTablist();
        TablistManager.loadOrder();
        sender.sendMessage(Component.text("§aTab mis à jour avec succès."));

        return true;
    }
}
