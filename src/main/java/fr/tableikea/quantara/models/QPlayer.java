package fr.tableikea.quantara.models;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.models.rank.Rank;
import fr.tableikea.quantara.models.rank.RankColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;

public class QPlayer {

    public static final Map<UUID, QPlayer> profilMap = new HashMap<>();

    private final UUID uuid;
    private final Player player;
    private Rank rank;
    private final UUID QuantID;

    public QPlayer(@NotNull Player player){
        this.uuid = player.getUniqueId();
        this.player = player;
        this.rank = Rank.JOUEUR;
        this.QuantID = UUID.randomUUID();

        if(!profilMap.containsKey(this.uuid)){
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".player", this.player.getName());
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".uuid", this.uuid.toString());
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".rank", this.rank.toString());
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".quantID", this.QuantID.toString());
            Main.getInstance().saveConfig();
            profilMap.put(this.uuid, this);
        }

    }

    public QPlayer(@NotNull UUID uuid, @NotNull Rank rank, @NotNull UUID quantID){
        this.uuid = uuid;
        this.player = Bukkit.getOfflinePlayer(uuid).getPlayer();
        this.rank = rank;
        this.QuantID = quantID;

    }

    public QPlayer(UUID uuid){
        this.uuid = uuid;
        this.player = Bukkit.getPlayer(uuid);
        this.rank = Rank.JOUEUR;
        this.QuantID = UUID.randomUUID();

        if(!profilMap.containsKey(this.uuid)){
            profilMap.put(this.uuid, this);
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".player", this.player.getName());
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".uuid", this.uuid.toString());
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".rank", this.rank.toString());
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".quantID", this.QuantID.toString());
            Main.getInstance().saveConfig();
        }

    }

    public QPlayer(@NotNull Player player, Rank rank){
        this.uuid = player.getUniqueId();
        this.player = player;
        this.rank = rank;
        this.QuantID = UUID.randomUUID();

        if(!profilMap.containsKey(this.uuid) || Main.getInstance().getConfig().getBoolean("settings.duplicate_profil")){
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".player", this.player.getName());
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".uuid", this.uuid.toString());
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".rank", this.rank.toString());
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".quantID", this.QuantID.toString());

            Main.getInstance().saveConfig();

            profilMap.put(this.uuid, this);
        }

    }

    public QPlayer(UUID uuid, Rank rank){
        this.uuid = uuid;
        this.player = Bukkit.getPlayer(uuid);
        this.rank = rank;
        this.QuantID = UUID.randomUUID();

        if(!profilMap.containsKey(this.uuid)){
            profilMap.put(this.uuid, this);
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".player", this.player.getName());
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".uuid", this.uuid.toString());
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".rank", this.rank.toString());
            Main.getInstance().getConfig().set("qprofils."+this.player.getUniqueId()+".quantID", this.QuantID.toString());
            Main.getInstance().saveConfig();
            profilMap.put(this.uuid, this);
        }

    }

    @Nullable
    public static QPlayer getProfilByUUID(UUID uuid){
        if(profilMap.containsKey(uuid)){return profilMap.get(uuid);}
        return null;
    }

    @Nullable
    public static QPlayer getProfilByPlayer(@NotNull Player player){
        UUID uuid = player.getUniqueId();
        if(profilMap.containsKey(uuid)){return profilMap.get(uuid);}
        return null;
    }

    @Nullable
    public static QPlayer getProfilByQuantID(UUID quantID){
        for(UUID uuid : profilMap.keySet()){
            if(profilMap.get(uuid).QuantID == quantID){return profilMap.get(uuid);}
        }
        return null;
    }

    public static @NotNull List<QPlayer> getAllProfil(){
        ArrayList<QPlayer> profils = new ArrayList<>();
        for(UUID uuid : profilMap.keySet()){profils.add(profilMap.get(uuid));}
        return profils;
    }

    public static @NotNull List<QPlayer> getAllPlayerByRank(Rank rank){
        ArrayList<QPlayer> profils = new ArrayList<>();
        for(UUID uuid : profilMap.keySet()){
            if(profilMap.get(uuid).rank == rank){profils.add(profilMap.get(uuid));}
        }
        return profils;
    }

    public static Map<UUID, QPlayer> getProfilMap() {
        return profilMap;
    }

    public static void removeProfilFromMap(UUID uuid){
        profilMap.remove(uuid);
    }

    public static void removeProfilFromMap(@NotNull Player player){
        UUID uuid = player.getUniqueId();
        profilMap.remove(uuid);
    }

    public UUID getUuid() {return uuid;}

    public Player getPlayer() {return player;}

    public Rank getRank() {return rank;}

    public UUID getQuantID(){
        return QuantID;
    }

    public void setRank(Rank rank) {this.rank = rank;}

    public static void saveProfilsToConfig() {
        for (UUID uuid : QPlayer.profilMap.keySet()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            QPlayer qPlayer = QPlayer.profilMap.get(uuid);

            // Sauvegarde dans la config
            Main.getInstance().getConfig().set("profilsMap." + player.getName() + ".uuid", uuid.toString());
            Main.getInstance().getConfig().set("profilsMap." + player.getName() + ".rank", qPlayer.getRank().toString());
            Main.getInstance().getConfig().set("profilsMap." + player.getName() + ".quantID", qPlayer.getQuantID().toString());
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
                String rankStr = playerSection.getString("rank");
                String quantIDStr = playerSection.getString("quantID");

                if (uuidStr == null || rankStr == null || quantIDStr == null) continue;

                try {
                    UUID uuid = UUID.fromString(uuidStr);
                    UUID quantID = UUID.fromString(quantIDStr);
                    Rank rank = Rank.valueOf(rankStr);

                    QPlayer qPlayer = new QPlayer(uuid, rank, quantID); // constructeur avec uuid et rank
                    // ❗ Injecte manuellement le quantID si nécessaire (à adapter si champ final)
                    // qPlayer.setQuantID(quantID); // si tu as un setter (tu ne l'as pas actuellement)

                    QPlayer.profilMap.put(uuid, qPlayer);

                } catch (IllegalArgumentException e) {
                    Bukkit.getLogger().warning("[QPlayer] Erreur lors du chargement du profil pour " + playerName);
                }
            }
        }
    }
}
