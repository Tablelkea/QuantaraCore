package fr.tableikea.quantara.managers;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.models.Rank;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.UUID;

public class RankManager {

    public static void setPlayerRank(UUID uuid, String rank){
        Player target = (Player) Bukkit.getOfflinePlayer(uuid);
        Rank permission = Rank.valueOf(rank);
        String path = "qprofils."+target.getUniqueId()+".";

        FileConfiguration config = Main.getInstance().getConfig();
        Main INSTANCE = Main.getInstance();

        config.set(path+"permission", rank);
        INSTANCE.saveConfig();
        INSTANCE.reloadConfig();

        switch (permission){
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
            }case VIP -> {
                PermissionAttachment perm = target.addAttachment(Main.getInstance());
                perm.setPermission("vip.use", true);
                System.out.println("Permission vip.perm: %s".formatted(target.hasPermission("vip.use")));
            }case PLAYER -> {
                PermissionAttachment perm = target.addAttachment(Main.getInstance());
                perm.setPermission("player.use", true);
                System.out.println("Permission player.perm: %s".formatted(target.hasPermission("player.use")));
            }
        }
    }

}
