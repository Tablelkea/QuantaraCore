package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadConfig implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        Main.getInstance().reloadConfig();
        sender.sendMessage(Main.getInstance().getConfig().get("messages.prefix")+"Le fichier de configuration a bien été rechargé.");

        return true;
    }
}
