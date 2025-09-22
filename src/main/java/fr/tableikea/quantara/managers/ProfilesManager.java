package fr.tableikea.quantara.managers;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.models.QProfile;
import fr.tableikea.quantara.models.Rank;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public class ProfilesManager {

    /*public static void saveProfilsToConfig() {
        for (UUID uuid : QProfile.profilMap.keySet()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            FileConfiguration config = Main.getInstance().getConfig();
            String path = "qprofils."+uuid+".";
            QProfile qPlayer = QProfile.profilMap.get(uuid);

            // Sauvegarde dans la config
            Main.getInstance().getConfig().set("profilsMap." + player.getName() + ".uuid", uuid.toString());
            Main.getInstance().getConfig().set("profilsMap." + player.getName() + ".permission", path+"permission");
            Main.getInstance().getConfig().set("profilsMap." + player.getName() + ".quantaraID", path+"quantaraID");
        }
        Main.getInstance().saveConfig();
    }

    public static void loadProfilsFromConfig() {
        ConfigurationSection section = Main.getInstance().getConfig().getConfigurationSection("profilsMap");
        if (section != null) {
            for (String playerName : section.getKeys(false)) {
                ConfigurationSection playerSection = section.getConfigurationSection(playerName);
                if (playerSection == null) continue;

                String uuidStr = playerSection.getString("uuid");
                String rankStr = playerSection.getString("permission");
                String quantIDStr = playerSection.getString("quantaraID");

                if (uuidStr == null || rankStr == null || quantIDStr == null) continue;

                try {
                    UUID uuid = UUID.fromString(uuidStr);
                    UUID quantID = UUID.fromString(quantIDStr);
                    Rank rank = Rank.valueOf(rankStr);

                    QProfile qPlayer = new QProfile(uuid, rank, quantID); // constructeur avec uuid et rank
                    // ❗ Injecte manuellement le quantID si nécessaire (à adapter si champ final)
                    // qPlayer.setQuantID(quantID); // si tu as un setter (tu ne l'as pas actuellement)

                    QProfile.profilMap.put(uuid, qPlayer);

                } catch (IllegalArgumentException e) {
                    Bukkit.getLogger().warning("[QPlayer] Erreur lors du chargement du profil pour " + playerName);
                }
            }
        }
    }*/

}
