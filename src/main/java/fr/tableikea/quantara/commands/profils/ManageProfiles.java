package fr.tableikea.quantara.commands.profils;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.models.QProfile;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ManageProfiles implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        FileConfiguration config = Main.getInstance().getConfig();

        if(command.getName().equalsIgnoreCase("createprofile")){
            Player target = Bukkit.getPlayer(args[0]);
            UUID targetUUID = target.getUniqueId();

            String profilPath = "qprofils."+targetUUID;

            if(config.get(profilPath) != null){
                new QProfile(targetUUID);

                sender.sendMessage(config.get("messages.prefix")+"Le profil de §e%s §7a été créer avec succès.".formatted(target.getName()));
            }else{
                sender.sendMessage(config.get("messages.prefix")+"§cCe profil éxiste déjà.");
            }


        }else if(command.getName().equalsIgnoreCase("removeprofile")){

            Player target = Bukkit.getPlayer(args[0]);
            UUID targetUUID = target.getUniqueId();

            String profilPath = "qprofils."+targetUUID;
            String path = "qprofils."+targetUUID+".";

            if(config.get(profilPath) != null){
                config.set(path, null);

                sender.sendMessage(config.get("messages.prefix")+"Le profil de §e%s §7a bien été supprimer.".formatted(target.getName()));
            }else{
                sender.sendMessage(config.get("messages.prefix")+"§cCe profil n'éxiste pas.");
            }
        }

        return true;
    }
}
