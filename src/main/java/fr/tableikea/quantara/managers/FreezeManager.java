package fr.tableikea.quantara.managers;

import fr.tableikea.quantara.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FreezeManager {

    private static final Set<UUID> frozenPlayers = new HashSet<>();

    public static void freeze(Player player) {
        FileConfiguration config = Main.getInstance().getConfig();

        frozenPlayers.add(player.getUniqueId());

        player.sendMessage(Component.text(
                config.getString("messages.prefix", "§7[§bQuantara§7] ") +
                        "§cVous êtes actuellement gelé par un modérateur."
        ));
    }

    public static void unfreeze(Player player) {
        FileConfiguration config = Main.getInstance().getConfig();

        frozenPlayers.remove(player.getUniqueId());

        player.sendMessage(Component.text(
                config.getString("messages.prefix", "§7[§bQuantara§7] ") +
                        "§aVous avez été libéré, vous pouvez de nouveau bouger."
        ));
    }

    public static boolean isFrozen(Player player) {
        return frozenPlayers.contains(player.getUniqueId());
    }


}
