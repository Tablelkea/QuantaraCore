package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.models.QPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ForceRemoveQProfil implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        String target = args[0];
        FileConfiguration config = Main.getInstance().getConfig();

        if(!sender.hasPermission("admin.use")) {
            sender.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
            return true;
        }

        if(config.get("qprofils."+Bukkit.getOfflinePlayer(target).getUniqueId()) != null){
            config.set("qprofils", null);
            QPlayer.removeProfilFromMap((Player) Bukkit.getOfflinePlayer(target));
            Main.getInstance().saveConfig();
            sender.sendMessage(config.get("messages.prefix")+"Le profil a bien été éffacer.");
        }else{
            sender.sendMessage(config.get("messages.prefix")+"§cLe profil demandé n'éxiste pas.");
        }


        return true;
    }
}
