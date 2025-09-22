package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class RestartServer extends BukkitRunnable implements CommandExecutor{

    private int seconds = 10;
    FileConfiguration messagesConfig = Main.getInstance().getMessagesConfig();

    public RestartServer(){
        Bukkit.broadcast(Component.text(messagesConfig.get("messages.prefix")+"Le serveur redémarre dans §e%s §7secondes".formatted(seconds)));
        for(Player player : Bukkit.getOnlinePlayers()){
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        }
    }

    @Override
    public void run() {
        if(seconds >0 && seconds <= 5){
            Bukkit.broadcast(Component.text(messagesConfig.get("messages.prefix")+"Le serveur redémarre dans §e%s §7seconde%s".formatted(seconds, seconds == 1 ? "" : "s")));
            for(Player player : Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
        }else if(seconds ==0){
            cancel();
            for(Player player : Bukkit.getOnlinePlayers()){
                player.kick(Component.text(
                        "\n§8§m----------------------------------------\n" +
                                "§r" + messagesConfig.get("messages.prefix") + "\n" +
                                "§r\n" +
                                "§c§lLe serveur redémarre...\n" +
                                "§6§oLes fichiers sont en cours d'actualisation.\n" +
                                "§7Merci de patienter avant de vous reconnecter.\n" +
                                "§r\n" +
                                "§8§m----------------------------------------"
                ));

            }
            Main.getInstance().getServer().restart();
            return;
        }

        seconds--;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        FileConfiguration messagesConfig = Main.getInstance().getMessagesConfig();

        if(!sender.hasPermission("admin.use")) {
            sender.sendMessage(Component.text(messagesConfig.getString("messages.prefix", "§7[§bQuantara§7] ") + "§cVous n'avez pas la permission."));
            return true;
        }

        RestartServer timer = new RestartServer();
        timer.runTaskTimer(Main.getInstance(), 0L, 20L);

        return true;
    }
}
