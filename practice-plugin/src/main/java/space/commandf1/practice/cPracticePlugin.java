package space.commandf1.practice;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.util.TimeStampMode;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.messaging.PluginMessageListener;
import space.commandf1.capi.bukkit.BukkitPlugin;
import space.commandf1.capi.loader.APILoader;
import space.commandf1.capi.stats.PluginStats;
import space.commandf1.capi.utils.NMSUtils;
import space.commandf1.practice.api.cPractice;
import space.commandf1.practice.commands.CommandMap;
import space.commandf1.practice.joinitem.JoinItem;
import space.commandf1.practice.listener.listeners.PlayerListener;
import space.commandf1.practice.settings.Setting;
import space.commandf1.practice.settings.Settings;

import java.io.File;
import java.util.logging.Logger;

/*
* 傻逼东西 爱谁写谁写
* */
public class cPracticePlugin extends BukkitPlugin {
    private static cPracticePlugin instance;
    private static API api;
    private static Logger logger;

    private static File arenasDir, dataDir;

    public static cPracticePlugin getInstance() {
        return instance;
    }

    public static Logger getPluginLogger() {
        return logger;
    }

    public static File getArenasDir() {
        return arenasDir;
    }

    public static API getAPI() {
        return api;
    }

    public static File getDataDir() {
        return dataDir;
    }

    public void onSetup() {
        logger = this.getLogger();

        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
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

        // setup packet-events
        PacketEvents.getAPI().getSettings().debug(false).bStats(false).checkForUpdates(false).timeStampMode(TimeStampMode.MILLIS).readOnlyListeners(false);
        PacketEvents.getAPI().init();

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
        dataDir = new File(this.getDataFolder(), "data");

        /*
        // examples
        World example = Bukkit.getWorlds().get(0);
        Serializer serializer = new Serializer(new Arena("example", new Location(example, 0, 0, 0), new Location(example, 0, 0, 0), new Location(example, 0, 0, 0)));
        serializer.writeTo(new File(arenasDir, "example.ser"));

        saveResource("example.yml", true);
        Config exampleConfig = new Config("example.yml", this);
        exampleConfig.setWithoutException("example", new Arena("example", new Location(example, 0, 0, 0), new Location(example, 0, 0, 0), new Location(example, 0, 0, 0)));
         */

        // clean world
        if ((boolean) Settings.CLEAN_WORLD.getObj()) {
            for (World world : Bukkit.getWorlds()) {
                world.setGameRuleValue("doDayLightCycle", "false");
                // world.setGameRuleValue("doWeatherCycle", "false");
                // 1.8 not found
                world.setGameRuleValue("doMobSpawning", "false");
                world.getEntities().stream().filter(entity -> entity.getType() != EntityType.PLAYER && entity.getType() != EntityType.ITEM_FRAME).forEach(Entity::remove);
                world.setStorm(false);
                world.setThundering(false);
                world.setTime(6000L);
            }
        }

        // register listener
        this.getListenerManager().registerListeners(new PlayerListener());

        // register join item
        JoinItem rankedQueueItem = new JoinItem((ItemStack) Settings.RANKED_QUEUE.getObj());
        rankedQueueItem.setAction(event -> event.setCancelled(true));
        rankedQueueItem.setI(0);
        rankedQueueItem.registerListeners();

        JoinItem unrankedQueueItem = new JoinItem((ItemStack) Settings.UNRANKED_QUEUE.getObj());
        unrankedQueueItem.setAction(event -> event.setCancelled(true));
        unrankedQueueItem.setI(1);
        unrankedQueueItem.registerListeners();

        // register commands
        this.getCommandManager().registerCommandMap(new CommandMap(), this);

        // successfully loaded
        logger.info("Plugin successfully loaded! (" + (System.currentTimeMillis() - startTime) + " ms)");
    }

    public void onExit() {
        instance = null;
        logger = null;
        arenasDir = null;
        api = null;

        PacketEvents.getAPI().terminate();
        Bukkit.getServer().getScheduler().cancelTasks(this);
    }

    public void registerPacketListeners(PacketListenerAbstract... listeners) {
        for (PacketListenerAbstract listener : listeners) {
            PacketEvents.getAPI().getEventManager().registerListener(listener);
        }
    }

    public void registerIncomingPluginChannel(String str, PluginMessageListener... pluginMessageListeners) {
        for (PluginMessageListener pluginMessageListener : pluginMessageListeners) {
            Bukkit.getMessenger().registerIncomingPluginChannel(this, str, pluginMessageListener);
        }
    }
}
