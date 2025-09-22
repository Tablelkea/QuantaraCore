package fr.tableikea.quantara.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class HomeManager {

    private final JavaPlugin plugin;
    private final File homesFile;
    private FileConfiguration homesConfig;


    // Structure: UUID -> (homeName -> Location)
    private final Map<UUID, Map<String, Location>> homes = new ConcurrentHashMap<>();


    // Teleport cooldowns: UUID -> lastTeleportMillis
    private final Map<UUID, Long> lastTeleport = new ConcurrentHashMap<>();


    private final int maxHomesPerPlayer;
    private final int teleportCooldownSeconds;

    public HomeManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.homesFile = new File(plugin.getDataFolder(), "homes.yml");
        this.maxHomesPerPlayer = plugin.getConfig().getInt("max-homes-per-player", 5);
        this.teleportCooldownSeconds = plugin.getConfig().getInt("home-teleport-cooldown-seconds", 30);


        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
        if (!homesFile.exists()) {
            try {
                homesFile.createNewFile();
                homesConfig = YamlConfiguration.loadConfiguration(homesFile);
                homesConfig.save(homesFile);
            } catch (IOException e) {
                plugin.getLogger().severe("Could not create homes.yml: " + e.getMessage());
            }
        }


        homesConfig = YamlConfiguration.loadConfiguration(homesFile);
        loadAll();


// Auto-save task
        int interval = plugin.getConfig().getInt("save-interval-seconds", 60);
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this::saveAll, interval * 20L, interval * 20L);
    }

    public synchronized void loadAll() {
        homes.clear();
        if (homesConfig == null) homesConfig = YamlConfiguration.loadConfiguration(homesFile);


        for (String key : homesConfig.getKeys(false)) {
            try {
                UUID uuid = UUID.fromString(key);
                ConfigurationSectionWrapper section = new ConfigurationSectionWrapper(homesConfig.getConfigurationSection(key));
                Map<String, Location> map = section.deserializeAllLocations();
                homes.put(uuid, new ConcurrentHashMap<>(map));
            } catch (Exception ignored) {
            }
        }
    }

    public synchronized void saveAll() {
        homesConfig = new YamlConfiguration();

        for (Map.Entry<UUID, Map<String, Location>> entry : homes.entrySet()) {
            String uuidStr = entry.getKey().toString();
            for (Map.Entry<String, Location> home : entry.getValue().entrySet()) {
                String path = uuidStr + "." + home.getKey();
                Location loc = home.getValue();

                homesConfig.set(path + ".world", loc.getWorld().getName());
                homesConfig.set(path + ".x", loc.getX());
                homesConfig.set(path + ".y", loc.getY());
                homesConfig.set(path + ".z", loc.getZ());
                homesConfig.set(path + ".yaw", loc.getYaw());
                homesConfig.set(path + ".pitch", loc.getPitch());
            }
        }

        try {
            homesConfig.save(homesFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save homes.yml: " + e.getMessage());
        }
    }


    public synchronized boolean setHome(Player player, String name, Location loc) {
        UUID uuid = player.getUniqueId();
        Map<String, Location> map = homes.computeIfAbsent(uuid, k -> new ConcurrentHashMap<>());
        if (!map.containsKey(name) && map.size() >= maxHomesPerPlayer) return false;
        map.put(name, loc);
        saveAll();
        return true;
    }


    public synchronized boolean deleteHome(Player player, String name) {
        UUID uuid = player.getUniqueId();
        Map<String, Location> map = homes.get(uuid);
        if (map == null) return false;
        Location removed = map.remove(name);
        if (removed == null) return false;
        saveAll();
        return true;
    }

    public synchronized List<String> listHomes(Player player) {
        UUID uuid = player.getUniqueId();
        Map<String, Location> map = homes.getOrDefault(uuid, Collections.emptyMap());
        return new ArrayList<>(map.keySet());
    }


    public synchronized Location getHomeLocation(UUID uuid, String name) {
        Map<String, Location> map = homes.get(uuid);
        if (map == null) return null;
        return map.get(name);
    }


    public boolean canTeleport(Player player) {
        Long last = lastTeleport.get(player.getUniqueId());
        if (last == null) return true;
        long elapsed = (System.currentTimeMillis() - last) / 1000L;
        return elapsed >= teleportCooldownSeconds;
    }

    public long getRemainingCooldownSeconds(Player player) {
        Long last = lastTeleport.get(player.getUniqueId());
        if (last == null) return 0;
        long elapsed = (System.currentTimeMillis() - last) / 1000L;
        long remaining = teleportCooldownSeconds - elapsed;
        return Math.max(0, remaining);
    }


    public void recordTeleport(Player player) {
        lastTeleport.put(player.getUniqueId(), System.currentTimeMillis());
    }

    // Inner helper to deserialize locations from ConfigurationSection
    private static class ConfigurationSectionWrapper {
        private final org.bukkit.configuration.ConfigurationSection section;


        public ConfigurationSectionWrapper(org.bukkit.configuration.ConfigurationSection section) {
            this.section = section;
        }


        public Map<String, Location> deserializeAllLocations() {
            Map<String, Location> map = new HashMap<>();
            if (section == null) return map;
            for (String homeName : section.getKeys(false)) {
                org.bukkit.configuration.ConfigurationSection s = section.getConfigurationSection(homeName);
                if (s == null) continue;
                try {
                    String world = s.getString("world");
                    double x = s.getDouble("x");
                    double y = s.getDouble("y");
                    double z = s.getDouble("z");
                    float yaw = (float) s.getDouble("yaw");
                    float pitch = (float) s.getDouble("pitch");
                    Location loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
                    map.put(homeName, loc);
                } catch (Exception ignored) {
                }
            }
            return map;
        }
    }
}
