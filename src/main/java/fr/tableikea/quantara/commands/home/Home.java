package fr.tableikea.quantara.commands.home;

import fr.tableikea.quantara.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Home implements CommandExecutor {

    private final Main plugin;

    public Home(Main plugin) {
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
        if (!player.hasPermission("hardium.home.use")) {
            player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
            return true;
        }
        if (args.length < 1) {
            player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cUsage: /home <nom>."));
            return true;
        }
        String name = args[0].toLowerCase();
        Location loc = plugin.getHomeManager().getHomeLocation(player.getUniqueId(), name);
        if (loc == null) {
            player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cHome introuvable: " + name));
            return true;
        }
        if (!plugin.getHomeManager().canTeleport(player)) {
            long rem = plugin.getHomeManager().getRemainingCooldownSeconds(player);
            player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous devez attendre " + rem + " secondes avant de vous téléporter à nouveau."));
            return true;
        }
        player.teleportAsync(loc).thenRun(() -> plugin.getHomeManager().recordTeleport(player));
        player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§aTéléportation vers '" + name));
        return true;
    }
}