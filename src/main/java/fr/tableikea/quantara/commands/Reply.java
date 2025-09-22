package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.managers.PrivateMessageManager;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Reply implements CommandExecutor {

    private final PrivateMessageManager manager;

    public Reply(PrivateMessageManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§7[§dMP§7] §cSeuls les joueurs peuvent utiliser cette commande.");
            return true;
        }
        Player from = (Player) sender;

        if (!from.hasPermission("hardium.msg")) {
            from.sendMessage("§7[§dMP§7] §cVous n’avez pas la permission.");
            return true;
        }

        if (args.length < 1) {
            from.sendMessage("§7[§dMP§7] §cUsage: /reply <message>");
            return true;
        }

        String message = String.join(" ", args);
        manager.reply(from, message);
        return true;
    }
}
