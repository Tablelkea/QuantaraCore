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
        sender.sendMessage("§6§lCouleurs disponibles :");
        sender.sendMessage("§4&4 §r- Rouge foncé");
        sender.sendMessage("§c&c §r- Rouge clair");
        sender.sendMessage("§6&6 §r- Or");
        sender.sendMessage("§e&e §r- Jaune");
        sender.sendMessage("§2&2 §r- Vert foncé");
        sender.sendMessage("§a&a §r- Vert clair");
        sender.sendMessage("§3&3 §r- Cyan foncé");
        sender.sendMessage("§b&b §r- Cyan clair");
        sender.sendMessage("§1&1 §r- Bleu foncé");
        sender.sendMessage("§9&9 §r- Bleu clair");
        sender.sendMessage("§5&5 §r- Rose foncé");
        sender.sendMessage("§d&d §r- Rose clair");
        sender.sendMessage("§7&7 §r- Gris clair");
        sender.sendMessage("§8&8 §r- Gris foncé");
        sender.sendMessage("§f&f §r- Blanc");
        sender.sendMessage("§0&0 §r- Noir");

        sender.sendMessage(" ");
        sender.sendMessage("§6§lFormats disponibles :");
        sender.sendMessage("§l&l §r- Gras");
        sender.sendMessage("§k&k §r- Magique");
        sender.sendMessage("§n&n §r- Souligné");
        sender.sendMessage("§m&m §r- Barré");
        sender.sendMessage("§o&o §r- Italique");
        sender.sendMessage("§r&r §r- Reset");


        return true;
    }
}
