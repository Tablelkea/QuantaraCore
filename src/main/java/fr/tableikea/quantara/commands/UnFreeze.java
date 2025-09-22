package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.managers.FreezeManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UnFreeze implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        FileConfiguration config = Main.getInstance().getConfig();

        if(!sender.hasPermission("hardium.freeze")) {
            sender.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
            return true;
        }

        if(args.length != 1) {
            sender.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cUsage: /freeze <joueur>."));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null) {
            sender.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cJoueur introuvable ou hors-ligne."));
            return true;
        }

        if(!FreezeManager.isFrozen(target)) {
            sender.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cLe joueur §e" + target.getName() + "§c n'est pas gelé."));
            return true;
        }
        FreezeManager.unfreeze(target);
        sender.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§aVous avez libéré §e" + target.getName()));

        return true;
    }
}
