package fr.tableikea.quantara.commands.profils;

import fr.tableikea.quantara.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class CheckProfile implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        FileConfiguration config = Main.getInstance().getConfig();
        UUID targetUUID = Bukkit.getPlayer(args[0]).getUniqueId();
        String profilPath = "qprofils."+targetUUID;
        String path = "qprofils."+targetUUID+".";

        if(config.get(profilPath) != null){
            String player = config.getString(path+"player");
            String permission = config.getString(path+"permission");
            String quantaraID = config.getString(path+"quantaraID");

            sender.sendMessage(
                    "§8§m───────────────\n§r"+
                    "§7Joueur: §b" + player+
                    "\n§7Unique ID du joueur: §b" + targetUUID +
                    "\n§7Permission: §b" + permission+
                    "\n§7QuantaraID: §b" + quantaraID+ "\n"+
                    "§8§m───────────────\n"
            );
        }

        return true;
    }
}
