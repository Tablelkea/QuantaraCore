package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class ReloadConfig implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        FileConfiguration messagesConfig = Main.getInstance().getMessagesConfig();

        if(!sender.hasPermission("moderateur.use")) {
            sender.sendMessage(Component.text(messagesConfig.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
            return true;
        }

        Main.getInstance().reloadConfig();
        Main.getInstance().reloadProfilesConfig();
        Main.getInstance().reloadMessagesConfig();
        sender.sendMessage(messagesConfig.get("messages.prefix")+"Le fichier de configuration a bien été rechargé.");

        return true;
    }
}
