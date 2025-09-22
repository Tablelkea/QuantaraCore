package fr.tableikea.quantara.commands.home;

import fr.tableikea.quantara.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
public class ListHomes implements CommandExecutor {
    private final Main plugin;
    public ListHomes(Main plugin) {
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
        if (!player.hasPermission("hardium.home.list")) {
            player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
            return true;
        }
        List<String> homes = plugin.getHomeManager().listHomes(player);
        if (homes.isEmpty()) {
            player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez aucune home."));
            return true;
        }
        player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§7Vos homes: §e" + String.join("§7,§e ", homes)));
        return true;
    }
}
