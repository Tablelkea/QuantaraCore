package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CheckProfil implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        Player target = (Player) Bukkit.getOfflinePlayer(args[0]);
        if (args.length < 2) {

            if(Main.getInstance().getConfig().get("qprofils."+target.getUniqueId()) != null){
                sender.sendMessage("--------- %s ---------".formatted(target));
                sender.sendMessage("§7Joueur§8: §e%s".formatted(Main.getInstance().getConfig().getString("qprofils." + target.getUniqueId() + ".player")));
                sender.sendMessage("§7UUID§8: §e%s".formatted(Main.getInstance().getConfig().getString("qprofils." + target.getUniqueId() + ".uuid")));
                sender.sendMessage("§7QuantID§8: §e%s".formatted(Main.getInstance().getConfig().getString("qprofils." + target.getUniqueId() + ".quantID")));
                sender.sendMessage("§7Grade§8: §e%s".formatted(Main.getInstance().getConfig().getString("qprofils." + target.getUniqueId() + ".rank")));
            }else{
                sender.sendMessage(Main.getInstance().getConfig().get("messages.prefix")+"§cCe joueur n'existe pas. Si c'est une erreur -> /forcecreate <player>");

            }

        } else {
            String settings = args[1];

            if(Main.getInstance().getConfig().get("qprofils."+target) != null){
                switch (settings) {
                    case "player" -> {
                        sender.sendMessage("--------- %s ---------".formatted(target));
                        sender.sendMessage("§7Joueur§8: §e%S".formatted(Main.getInstance().getConfig().get("qprofils." + target.getUniqueId()+ ".player")));
                    }
                    case "uuid" -> {
                        sender.sendMessage("--------- %s ---------".formatted(target));
                        sender.sendMessage("§7UUID§8: §e%s".formatted(Main.getInstance().getConfig().get("qprofils." + target.getUniqueId()+ ".uuid")));
                    }
                    case "quantid" -> {
                        sender.sendMessage("--------- %s ---------".formatted(target));
                        sender.sendMessage("§7QuantID§8: §e%s".formatted(Main.getInstance().getConfig().get("qprofils." + target.getUniqueId()+ ".quantID")));
                    }
                    case "rank" -> {
                        sender.sendMessage("--------- %s ---------".formatted(target));
                        sender.sendMessage("§7Grade§8: §e%s".formatted(Main.getInstance().getConfig().get("qprofils." + target.getUniqueId()+ ".rank")));
                    }
                    case null, default -> sender.sendMessage("§cErreur d'argument(s) §8-> §7/check <player> <player|uuid|quandid|rank>");
                }
            }else{
                sender.sendMessage(Main.getInstance().getConfig().get("messages.prefix")+"Ce joueur n'existe pas. Si c'est une erreur -> /forcecreate <player>");
            }

        }


        return true;
    }
}
