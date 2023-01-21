package space.commandf1.practice.listener;

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
                // ListenerManager.getInstance().registerListeners(listener);
                cPracticePlugin.getInstance().registerListeners(listener);
                continue;
            }

            cPracticePlugin.getInstance().registerPacketListeners(listener);
        }
    }

    public void registerListeners(Listener... listeners) {
        cPracticePlugin.getInstance().registerListeners(listeners);
    }

    public static ListenerManager getInstance() {
        return instance;
    }
}
