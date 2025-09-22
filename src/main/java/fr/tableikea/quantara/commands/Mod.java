package fr.tableikea.quantara.commands;

import com.google.common.base.Joiner;
import fr.tableikea.quantara.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Mod implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        FileConfiguration messagesConfig = Main.getInstance().getMessagesConfig();

        if(!sender.hasPermission("moderateur.use")) {
            sender.sendMessage(Component.text(messagesConfig.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
            return true;
        }

        if(args.length < 2){
            sender.sendMessage(messagesConfig.get("messages.prefix")+"§cVeuillez ajoutez plus de contenue a votre broadcast.");
        }else{
            String message = Joiner.on(" ").join(args);
            Bukkit.broadcast(Component.text("§8§l[§4%s§8§l] §b%s".formatted(sender.getName(), message).replace("&", "§")));
            for(Player player : Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
        }

        return true;
    }
}
