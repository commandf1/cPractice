package space.commandf1.practice;

import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import space.commandf1.capi.bukkit.BukkitPlugin;
import space.commandf1.capi.loader.APILoader;
import space.commandf1.capi.stats.PluginStats;
import space.commandf1.capi.utils.NMSUtils;
import space.commandf1.practice.api.cPractice;
import space.commandf1.practice.settings.Setting;
import space.commandf1.practice.settings.Settings;

import java.io.File;
import java.util.logging.Logger;

public class cPracticePlugin extends BukkitPlugin {
    private static cPracticePlugin instance;
    private static API api;
    private static Logger logger;

    private static File arenasDir;

    public static cPracticePlugin getInstance() {
        return instance;
    }


    public static Logger getPluginLogger() {
        return logger;
    }

    public void onSetup() {
        logger = this.getLogger();
    }

    public void onStart() {
        instance = this;

        long startTime = System.currentTimeMillis();
        logger.info("Plugin loading...");

        if (!NMSUtils.getVersion().equalsIgnoreCase("v1_8_R3")) {
            logger.severe("This plugin can only run on 1.8 server!");
            logger.severe("The version of the server is " + NMSUtils.getVersion());
            logger.severe("Plugin unloading...");
            this.getPluginManager().unloadPlugin(this);
            return;
        }

        if (!PluginStats.IS_PLACEHOLDERAPI_INSTALL) {
            logger.warning("PlaceholderAPI was not loaded!");
        }

        // load cAPI
        APILoader.init(this);

        // setup configs
        this.saveResource("settings.yml", false);

        Setting.init(this);
        Settings.init();

        // register api
        api = new API(this);
        Bukkit.getServicesManager().register(cPractice.class, api, this, ServicePriority.Highest);

        // setup date
        this.mkdirs("arenas");
        arenasDir = new File(this.getDataFolder(), "arenas");

        /*
        // examples
        World example = Bukkit.getWorlds().get(0);
        Serializer serializer = new Serializer(new Arena("example", new Location(example, 0, 0, 0), new Location(example, 0, 0, 0), new Location(example, 0, 0, 0)));
        serializer.writeTo(new File(arenasDir, "example.ser"));

        saveResource("example.yml", true);
        Config exampleConfig = new Config("example.yml", this);
        exampleConfig.setWithoutException("example", new Arena("example", new Location(example, 0, 0, 0), new Location(example, 0, 0, 0), new Location(example, 0, 0, 0)));
         */

        // successfully loaded
        logger.info("Plugin successfully loaded! (" + (System.currentTimeMillis() - startTime) + " ms)");
    }

    public void onExit() {
        instance = null;
        logger = null;
    }
}
