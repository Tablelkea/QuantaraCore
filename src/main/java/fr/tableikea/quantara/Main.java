package fr.tableikea.quantara;

import fr.tableikea.quantara.commands.*;
import fr.tableikea.quantara.listeners.PlayerJoinListener;
import fr.tableikea.quantara.models.QPlayer;
import fr.tableikea.quantara.scoreboard.QScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public static boolean debugMode;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        System.out.println(this.getConfig().get("messages.system.onEnable", "§cErreur du chargement du message."));
        debugMode = getInstance().getConfig().getBoolean("settings.debug_mode");

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);

        getCommand("checkprofil").setExecutor(new CheckProfil());
        getCommand("color").setExecutor(new Color());
        getCommand("createprofil").setExecutor(new ForceCreateQProfil());
        getCommand("removeprofil").setExecutor(new ForceRemoveQProfil());
        getCommand("mod").setExecutor(new Mod());
        getCommand("perm").setExecutor(new Perm());
        getCommand("reloadconfig").setExecutor(new ReloadConfig());
        getCommand("resetstatistic").setExecutor(new ResetStatistic());
        getCommand("restartserver").setExecutor(new RestartServer());
        getCommand("updatescoreboard").setExecutor(new UpdateScoreboard());

        QPlayer.loadProfilsFromConfig();

        QScoreboard.startScoreboardUpdater();
    }

    @Override
    public void onDisable() {

        QPlayer.saveProfilsToConfig();

    }

    public static Main getInstance() {
        return instance;
    }
}
