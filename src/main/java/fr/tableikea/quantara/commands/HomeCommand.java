package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeCommand implements CommandExecutor {

    private final Main plugin;

    public HomeCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        FileConfiguration config = Main.getInstance().getMessagesConfig();

        if(command.getName().equalsIgnoreCase("sethome")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("[MP] Seuls les joueurs peuvent utiliser cette commande.");
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission("player.use")) {
                player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
                return true;
            }
            if (args.length < 1) {
                player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cUsage: /sethome <nom>."));
                return true;
            }
            String name = args[0].toLowerCase();
            boolean ok = plugin.getHomeManager().setHome(player, name, player.getLocation());
            if (!ok) {
                player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous avez atteint le nombre maximum de homes."));
                return true;
            }
            player.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§aHome §e" + name + " §adéfini."));
            return true;
        }else if(command.getName().equalsIgnoreCase("home")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("[MP] Seuls les joueurs peuvent utiliser cette commande.");
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission("player.use")) {
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
        }else if(command.getName().equalsIgnoreCase("delhome")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("[MP] Seuls les joueurs peuvent utiliser cette commande.");
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission("player.use")) {
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
        }else if(command.getName().equalsIgnoreCase("listhome")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("[MP] Seuls les joueurs peuvent utiliser cette commande.");
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission("player.use")) {
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

        return false;

    }
}
