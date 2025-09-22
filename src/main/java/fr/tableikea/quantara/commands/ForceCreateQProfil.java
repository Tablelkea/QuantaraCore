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

public class ForceCreateQProfil implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        FileConfiguration config = Main.getInstance().getConfig();

        if(!sender.hasPermission("admin.use")) {
            sender.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

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
