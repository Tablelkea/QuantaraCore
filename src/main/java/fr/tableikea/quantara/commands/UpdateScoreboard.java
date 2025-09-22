package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.managers.ScoreboardManager;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class UpdateScoreboard implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration messagesConfig = Main.getInstance().getMessagesConfig();

        if(!sender.hasPermission("moderateur.use")) {
            sender.sendMessage(Component.text(messagesConfig.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
            return true;
        }

        ScoreboardManager.updateAllScoreboards();
        sender.sendMessage(Component.text("§aScoreboards mis à jour avec succès."));
        return true;
    }
}
