package space.commandf1.practice;

import org.bukkit.plugin.java.JavaPlugin;
import space.commandf1.practice.api.cPractice;
import space.commandf1.practice.settings.Settings;

public class API implements cPractice {
    @SuppressWarnings("all")
    private final JavaPlugin plugin;

    public API(cPracticePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean isEnabled() {
        return (boolean) Settings.API_ENABLE.getObj();
    }

    @Override
    public String getVersion() {
        return cPracticePlugin.getInstance().getDescription().getVersion();
    }
}
