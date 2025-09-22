package fr.tableikea.quantara;

import fr.tableikea.quantara.commands.*;
import fr.tableikea.quantara.commands.home.DelHome;
import fr.tableikea.quantara.commands.home.Home;
import fr.tableikea.quantara.commands.home.ListHomes;
import fr.tableikea.quantara.commands.home.SetHome;
import fr.tableikea.quantara.listeners.FreezeListener;
import fr.tableikea.quantara.listeners.PlayerJoinListener;
import fr.tableikea.quantara.managers.HomeManager;
import fr.tableikea.quantara.managers.PrivateMessageManager;
import fr.tableikea.quantara.models.QPlayer;
import fr.tableikea.quantara.scoreboard.QScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public static boolean debugMode;

    private PrivateMessageManager pmManager;
    private HomeManager homeManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        System.out.println(this.getConfig().get("messages.system.onEnable", "§cErreur du chargement du message."));
        debugMode = getInstance().getConfig().getBoolean("settings.debug_mode");

        pmManager = new PrivateMessageManager();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new FreezeListener(), this);


        getCommand("freeze").setExecutor(new Freeze());
        getCommand("unfreeze").setExecutor(new UnFreeze());
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
        getCommand("msg").setExecutor(new Msg(pmManager));
        getCommand("reply").setExecutor(new Reply(pmManager));
        getCommand("togglemp").setExecutor(new ToggleMP(pmManager));

        getCommand("sethome").setExecutor(new SetHome(this));
        getCommand("home").setExecutor(new Home(this));
        getCommand("delhome").setExecutor(new DelHome(this));
        getCommand("listhomes").setExecutor(new ListHomes(this));

        this.homeManager = new HomeManager(this);

        QPlayer.loadProfilsFromConfig();

        QScoreboard.startScoreboardUpdater();
    }

    @Override
    public void onDisable() {
        if (homeManager != null) homeManager.saveAll();
        QPlayer.saveProfilsToConfig();

    }

    public static Main getInstance() {
        return instance;
    }

    public PrivateMessageManager getPmManager() {
        return pmManager;
    }

    public HomeManager getHomeManager() {
        return homeManager;
    }
}
