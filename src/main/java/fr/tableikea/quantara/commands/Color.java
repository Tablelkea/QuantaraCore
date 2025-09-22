package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class Color implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        FileConfiguration config = Main.getInstance().getConfig();

        if(!sender.hasPermission("vip.use")) {
            sender.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
            return true;
        }
        sender.sendMessage("§4&4 - Rouge foncé §c&c - Rouge clair §6&6 - Or §e&e - Jaune §2&a - Vert foncé §a&a - Vert clair §b&b - Cyan clair §3&3 - Cyan foncé §9&9 - Bleu clair §1&1 - Bleu foncé §d&d - Rose clair §5&5 - Rose foncé §f&f - Blanc §7&7 - Gris clair §8&8 - Gris foncé §0&0 - Noir");
        sender.sendMessage("§l&l - Gras§r §k&k - Magique§r §n&n - Souligné§r §m&m - Barré§r §o&o - Italique§r &r - Reset");

        return true;
    }
}
