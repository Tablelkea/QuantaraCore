package fr.tableikea.quantara.managers;

import org.bukkit.entity.Player;

import java.util.*;

public class PrivateMessageManager {

    private final Map<UUID, UUID> lastMessenger = new HashMap<>();
    private final Set<UUID> pmDisabled = new HashSet<>();


    public boolean sendMessage(Player from, Player to, String message) {
        if (pmDisabled.contains(to.getUniqueId())) {
            from.sendMessage("§7[§dMP§7] §c" + to.getName() + " a désactivé les messages privés.");
            return false;
        }

        String formattedFrom = "§7[§dMP§7] §dMoi §7→ §d" + to.getName() + "§7: §f" + message;
        String formattedTo = "§7[§dMP§7] §d" + from.getName() + " §7→ §dMoi§7: §f" + message;

        from.sendMessage(formattedFrom);
        to.sendMessage(formattedTo);

        lastMessenger.put(from.getUniqueId(), to.getUniqueId());
        lastMessenger.put(to.getUniqueId(), from.getUniqueId());


        return true;
    }


    public boolean reply(Player from, String message) {
        UUID last = lastMessenger.get(from.getUniqueId());
        if (last == null) {
            from.sendMessage("§7[§dMP§7] §cAucun joueur à qui répondre.");
            return false;
        }

        Player to = from.getServer().getPlayer(last);
        if (to == null || !to.isOnline()) {
            from.sendMessage("§7[§dMP§7] §cLe joueur n’est plus connecté.");
            return false;
        }

        return sendMessage(from, to, message);
    }


    public boolean toggleReception(Player player) {
        UUID id = player.getUniqueId();
        if (pmDisabled.contains(id)) {
            pmDisabled.remove(id);
            player.sendMessage("§7[§dMP§7] §aVous acceptez désormais les messages privés.");
            return true;
        } else {
            pmDisabled.add(id);
            player.sendMessage("§7[§dMP§7] §cVous refusez désormais les messages privés.");
            return false;
        }
    }

    public boolean isReceptionEnabled(Player player) {
        return !pmDisabled.contains(player.getUniqueId());
    }
}
