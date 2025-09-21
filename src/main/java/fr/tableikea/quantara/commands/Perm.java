package fr.tableikea.quantara.commands;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.models.QPlayer;
import fr.tableikea.quantara.models.rank.Rank;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Perm implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        Player target = (Player) Bukkit.getOfflinePlayer(args[0]);
        Rank rank = Rank.valueOf(args[1]);

        QPlayer.getProfilByPlayer(target).setRank(rank);
        Main.getInstance().getConfig().set("qprofils."+target.getUniqueId()+".rank", rank.toString());
        Main.getInstance().saveConfig();
        Main.getInstance().reloadConfig();
        sender.sendMessage(Main.getInstance().getConfig().get("messages.prefix")+"Vous avez changer les permissions de §e%s §7pour §b%s".formatted(target.getName(), rank));
        target.kick(Component.text(Main.getInstance().getConfig().get("messages.prefix")+"\n§6Votre grade a été modifié.\n§eveuillez vous reconnecter."));

        return true;
    }
}
