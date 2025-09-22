package fr.tableikea.quantara.commands.privateMessages;

import fr.tableikea.quantara.managers.PrivateMessageManager;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class ToggleMP implements CommandExecutor {

    private final PrivateMessageManager manager;

    public ToggleMP(PrivateMessageManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§7[§dMP§7] §cSeuls les joueurs peuvent utiliser cette commande.");
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("hardium.togglepm")) {
            player.sendMessage("§7[§dMP§7] §cVous n’avez pas la permission.");
            return true;
        }

        manager.toggleReception(player);
        return true;
    }
}
