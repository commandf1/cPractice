package space.commandf1.practice.settings;

import org.bukkit.plugin.java.JavaPlugin;
import space.commandf1.capi.config.Config;

public class Setting {
    private static Config settings, items;

    public static void init(JavaPlugin plugin) {
        settings = new Config("settings.yml", plugin);
        items = new Config("items.yml", plugin);
    }

    public static Config getSettings() {
        return settings;
    }

    public static Config getItems() {
        return items;
    }
}
