package fr.tableikea.quantara.models;

import fr.tableikea.quantara.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QProfile {

    private final static Main INSTANCE = Main.getInstance();
    public final static FileConfiguration config = Main.getInstance().getProfilesConfig();

    public static final Map<UUID, QProfile> profilMap = new HashMap<>();

    private final UUID playerUUID;
    private final Player player;
    private Rank permission;
    private final UUID quantaraID;

    public QProfile(UUID uuid){
        this.playerUUID = uuid;
        this.player = Bukkit.getPlayer(uuid);
        this.permission = Rank.PLAYER;
        this.quantaraID = UUID.randomUUID();

        profilMap.put(uuid, this);
        saveProfile(uuid);
    }

    public QProfile(@NotNull UUID uuid, Rank permission, UUID quantaraID){
        this.player = Bukkit.getPlayer(uuid);
        this.permission = permission;
        this.quantaraID = quantaraID;
        this.playerUUID = uuid;

        profilMap.put(uuid, this);
        saveProfile(uuid);
    }

    public static void saveProfile(UUID uuid){
        QProfile playerProfil = profilMap.get(uuid);

        config.set("qprofils."+uuid+".player", playerProfil.getPlayer().getName());
        config.set("qprofils."+uuid+".permission", playerProfil.getPermission().toString());
        config.set("qprofils."+uuid+".quantaraID", playerProfil.getQuantaraID().toString());

        INSTANCE.saveConfig();
    }

    public static @NotNull QProfile getPlayerProfile(UUID uuid){
        String path = "qprofils."+uuid+".";

        String player = config.getString(path+"player");
        String permission = config.getString(path+"permission");
        String quantaraID = config.getString(path+"quantaraID");

        if(player == null || permission == null | quantaraID == null ){
            return new QProfile(uuid);
        }
        return new QProfile(uuid, Rank.valueOf(permission), UUID.fromString(quantaraID));
    }

    public static void setPermission(UUID uuid, String permission){
        String path = "qprofils."+uuid+".";
        String getPermission = config.getString(path+"permission");

        if(Arrays.stream(Rank.values().clone()).toList().contains(Rank.valueOf(permission)) && getPermission != null){
            config.set(path + "permission", permission);

        }
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public Player getPlayer() {
        return player;
    }

    public Rank getPermission() {
        return permission;
    }

    public void setPermission(Rank permission) {
        this.permission = permission;
    }

    public UUID getQuantaraID() {
        return quantaraID;
    }
}
