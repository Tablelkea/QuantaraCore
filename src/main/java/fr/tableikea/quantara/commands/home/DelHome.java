package fr.tableikea.quantara.commands.home;

import fr.tableikea.quantara.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DelHome implements CommandExecutor {
    private final Main plugin;
    public DelHome(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        FileConfiguration config = Main.getInstance().getConfig();

        if (!(sender instanceof Player)) {
            sender.sendMessage("[MP] Seuls les joueurs peuvent utiliser cette commande.");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("hardium.home.del")) {
            player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
            return true;
        }
        if (args.length < 1) {
            player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cUsage: /delhome <nom>."));
            return true;
        }
        String name = args[0].toLowerCase();

        boolean ok = plugin.getHomeManager().deleteHome(player, name);
        if (!ok) {
            player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cHome introuvable: " + name));
            return true;
        }
        player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§aHome '" + name + "' supprimée."));
        return true;
    }
}