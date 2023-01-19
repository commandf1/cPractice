package space.commandf1.practice.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import space.commandf1.practice.cPracticePlugin;

public class ListenerManager {
    private static final ListenerManager instance = new ListenerManager();

    public void registerListeners(PluginListener... listeners) {
        for (PluginListener listener : listeners) {
            if (listener.getType().equals(ListenerType.ALL)) {
                ListenerManager.getInstance().registerListeners(listener);
                cPracticePlugin.getInstance().registerPacketListeners(listener);
                continue;
            }

            if (listener.getType().equals(ListenerType.BUKKIT)) {
                ListenerManager.getInstance().registerListeners(listener);
                continue;
            }

            cPracticePlugin.getInstance().registerPacketListeners(listener);
        }
    }

    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, cPracticePlugin.getInstance());
        }
    }

    public static ListenerManager getInstance() {
        return instance;
    }
}
