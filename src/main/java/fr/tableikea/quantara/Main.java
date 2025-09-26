package fr.tableikea.quantara;

import fr.tableikea.quantara.commands.*;
import fr.tableikea.quantara.commands.Color;
import fr.tableikea.quantara.commands.GlobalUpdate;
import fr.tableikea.quantara.commands.Mod;
import fr.tableikea.quantara.commands.Perm;
import fr.tableikea.quantara.commands.ReloadConfig;
import fr.tableikea.quantara.commands.ResetStatistic;
import fr.tableikea.quantara.commands.RestartServer;
import fr.tableikea.quantara.commands.UpdateScoreboard;
import fr.tableikea.quantara.commands.UpdateTablist;
import fr.tableikea.quantara.listeners.FreezeListener;
import fr.tableikea.quantara.listeners.playerEvent.PlayerJoinListener;
import fr.tableikea.quantara.managers.HomeManager;
import fr.tableikea.quantara.managers.PrivateMessageManager;
import fr.tableikea.quantara.managers.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private static Main instance;
    public static boolean debugMode;

    private FileConfiguration profilesConfig;
    private File profilesFile = new File(getDataFolder(), "profiles.yml");

    private FileConfiguration messagesConfig;
    private File messagesFile = new File(getDataFolder(), "messages.yml");

    private FileConfiguration guiConfig;
    private File guiFile = new File(getDataFolder(), "gui.yml");

    private PrivateMessageManager pmManager;
    private HomeManager homeManager;

    @Override
    public void onEnable() {

        instance = this;
        saveDefaultConfig();

        reloadMessagesConfig();
        reloadProfilesConfig();
        reloadGuiConfig();

        System.out.println(this.getConfig().getString("messages.system.onEnable", "§cErreur du chargement du message."));
        debugMode = getInstance().getConfig().getBoolean("settings.debug_mode");

        pmManager = new PrivateMessageManager();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new FreezeListener(), this);


        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("unfreeze").setExecutor(new FreezeCommand());

        getCommand("checkprofile").setExecutor(new ManageProfiles());
        getCommand("createprofile").setExecutor(new ManageProfiles());
        getCommand("removeprofile").setExecutor(new ManageProfiles());

        getCommand("color").setExecutor(new Color());

        getCommand("msg").setExecutor(new MsgCommand(pmManager));
        getCommand("reply").setExecutor(new MsgCommand(pmManager));
        getCommand("togglemp").setExecutor(new MsgCommand(pmManager));

        getCommand("sethome").setExecutor(new HomeCommand(this));
        getCommand("home").setExecutor(new HomeCommand(this));
        getCommand("delhome").setExecutor(new HomeCommand(this));
        getCommand("listhomes").setExecutor(new HomeCommand(this));

        getCommand("mod").setExecutor(new Mod());
        getCommand("perm").setExecutor(new Perm());
        getCommand("reloadconfig").setExecutor(new ReloadConfig());
        getCommand("resetstatistic").setExecutor(new ResetStatistic());
        getCommand("restartserver").setExecutor(new RestartServer());
        getCommand("updatescoreboard").setExecutor(new UpdateScoreboard());


        getCommand("updatetab").setExecutor(new UpdateTablist());
        getCommand("updateglobal").setExecutor(new GlobalUpdate());
        getCommand("manage").setExecutor(new ManagePlayer());

        this.homeManager = new HomeManager(this);


        ScoreboardManager.startScoreboardUpdater();
    }

    @Override
    public void onDisable() {
        if (homeManager != null) homeManager.saveAll();
        //ProfilesManager.saveProfilsToConfig();

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

    public void reloadProfilesConfig(){
        if(!this.profilesFile.exists()){
            this.saveResource("profiles.yml", false);
        }
        this.profilesConfig = YamlConfiguration.loadConfiguration(profilesFile);
    }

    public FileConfiguration getProfilesConfig(){
        return this.profilesConfig;
    }

    public void reloadMessagesConfig(){
        if(!this.messagesFile.exists()){
            this.saveResource("messages.yml", false);
        }
        this.messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public FileConfiguration getMessagesConfig(){
        return this.messagesConfig;
    }

    public void reloadGuiConfig(){
        if(!this.guiFile.exists()){
            this.saveResource("gui.yml", false);
        }
        this.guiConfig = YamlConfiguration.loadConfiguration(guiFile);
    }

    public FileConfiguration getGuiConfig(){
        return this.guiConfig;
    }
}
