package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.scoreboard.QScoreboard;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UpdateScoreboard implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        QScoreboard.updateAllScoreboards();
        sender.sendMessage(Component.text("§aScoreboards mis à jour avec succès."));
        return true;
    }
}
