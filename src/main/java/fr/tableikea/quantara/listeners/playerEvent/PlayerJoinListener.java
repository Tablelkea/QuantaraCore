package fr.tableikea.quantara.listeners.playerEvent;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.managers.TablistManager;
import fr.tableikea.quantara.models.QProfile;
import fr.tableikea.quantara.managers.ScoreboardManager;
import fr.tableikea.quantara.models.Rank;
import fr.tableikea.quantaraAuth.QuantaraAuth;
import fr.tableikea.quantaraAuth.models.ProfileManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration config = Main.getInstance().getConfig();
        FileConfiguration messagesConfig = Main.getInstance().getMessagesConfig();
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        String path = "qprofils."+player.getUniqueId();

        QProfile profile = ProfileManager.getProfile(playerUUID);
        ProfileManager.saveProfile(profile);

        // Charger scoreboard et tablist
        ScoreboardManager.loadScoreBoard(player);
        TablistManager.loadTablist();
        TablistManager.loadOrder();
        ScoreboardManager.updateAllScoreboards();

        for(Player pl : Bukkit.getOnlinePlayers()){
            ScoreboardManager.updateScoreboard(pl);

            Rank rank = Rank.valueOf(config.getString("qprofils."+pl.getUniqueId()+".permission"));

            switch (rank){
                case ADMIN ->  {
                    PermissionAttachment perm = pl.addAttachment(Main.getInstance());
                    perm.setPermission("admin.use", true);
                }case MODERATEUR -> {
                    PermissionAttachment perm = pl.addAttachment(Main.getInstance());
                    perm.setPermission("moderateur.use", true);
                }case HELPER -> {
                    PermissionAttachment perm = pl.addAttachment(Main.getInstance());
                    perm.setPermission("helper.use", true);
                }case VIP -> {
                    PermissionAttachment perm = pl.addAttachment(Main.getInstance());
                    perm.setPermission("vip.use", true);
                }case PLAYER ->{
                    PermissionAttachment perm = pl.addAttachment(Main.getInstance());
                    perm.setPermission("player.use", true);
                }case null, default -> {}
            }

            pl.recalculatePermissions();
            pl.updateCommands(); // Paper uniquement
        }
    }
}
