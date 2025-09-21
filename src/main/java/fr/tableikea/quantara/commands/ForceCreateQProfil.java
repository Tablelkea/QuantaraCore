package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.models.QPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ForceCreateQProfil implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        Player target = Bukkit.getPlayer(args[0]);

        FileConfiguration config = Main.getInstance().getConfig();

        if(target != null && config.get("qprofils."+target.getUniqueId()) == null){
            new QPlayer(target);
            sender.sendMessage(config.get("messages.prefix")+"Le profil a bien été créer de force pour §e%s§7.".formatted(target.getName()));
        }else if(target == null){
            sender.sendMessage(config.get("messages.prefix")+"§cLe joueur n'éxiste pas.");
        }else{
            sender.sendMessage(config.get("messages.prefix")+"§cCe profil éxiste déjà.");
        }


        return true;
    }
}
