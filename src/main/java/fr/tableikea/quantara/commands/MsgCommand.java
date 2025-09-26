package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.managers.PrivateMessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MsgCommand implements CommandExecutor {

    private final PrivateMessageManager manager;

    public MsgCommand(PrivateMessageManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if(command.getName().equalsIgnoreCase("msg")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("§7[§dMP§7] §cSeuls les joueurs peuvent utiliser cette commande.");
                return true;
            }
            Player from = (Player) sender;

            if (!from.hasPermission("player.use")) {
                from.sendMessage("§7[§dMP§7] §cVous n’avez pas la permission.");
                return true;
            }

            if (args.length < 2) {
                from.sendMessage("§7[§dMP§7] §cUsage: /msg <joueur> <message>");
                return true;
            }

            Player to = Bukkit.getPlayer(args[0]);
            if (to == null || !to.isOnline()) {
                from.sendMessage("§7[§dMP§7] §cJoueur introuvable.");
                return true;
            }

            String message = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));
            manager.sendMessage(from, to, message);
            return true;
        }else if(command.getName().equalsIgnoreCase("reply")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("§7[§dMP§7] §cSeuls les joueurs peuvent utiliser cette commande.");
                return true;
            }
            Player from = (Player) sender;

            if (!from.hasPermission("player.use")) {
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
        }else if(command.getName().equalsIgnoreCase("tooglemp")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("§7[§dMP§7] §cSeuls les joueurs peuvent utiliser cette commande.");
                return true;
            }
            Player player = (Player) sender;

            if (!player.hasPermission("player.use")) {
                player.sendMessage("§7[§dMP§7] §cVous n’avez pas la permission.");
                return true;
            }

            manager.toggleReception(player);
            return true;
        }

        return true;
    }
}
