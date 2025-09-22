package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.models.QPlayer;
import fr.tableikea.quantara.models.rank.Rank;
import io.papermc.paper.plugin.PermissionManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.NotNull;

public class Perm implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        FileConfiguration config = Main.getInstance().getConfig();

        if(!sender.hasPermission("admin.use")) {
            sender.sendMessage(Component.text(config.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
            return true;
        }

        Player target = (Player) Bukkit.getOfflinePlayer(args[0]);
        Rank rank = Rank.valueOf(args[1]);

        QPlayer.getProfilByPlayer(target).setRank(rank);
        Main.getInstance().getConfig().set("qprofils."+target.getUniqueId()+".rank", rank.toString());
        Main.getInstance().saveConfig();
        Main.getInstance().reloadConfig();
        sender.sendMessage(Main.getInstance().getConfig().get("messages.prefix")+"Vous avez changer les permissions de §e%s §7pour §b%s".formatted(target.getName(), rank));
        target.kick(Component.text(Main.getInstance().getConfig().get("messages.prefix")+"\n§6Votre grade a été modifié.\n§eveuillez vous reconnecter."));

        switch (rank){
            case ADMIN ->  {
                PermissionAttachment perm = target.addAttachment(Main.getInstance());
                perm.setPermission("admin.use", true);
                System.out.println("Permission admin.perm: %s".formatted(target.hasPermission("admin.use")));
            }case MODERATEUR -> {
                PermissionAttachment perm = target.addAttachment(Main.getInstance());
                perm.setPermission("moderateur.use", true);
                System.out.println("Permission moderateur.perm: %s".formatted(target.hasPermission("moderateur.use")));
            }case HELPER -> {
                PermissionAttachment perm = target.addAttachment(Main.getInstance());
                perm.setPermission("helper.use", true);
                System.out.println("Permission helper.perm: %s".formatted(target.hasPermission("helper.use")));
            }
        }

        return true;
    }
}
