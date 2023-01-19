package space.commandf1.practice.listener;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import org.bukkit.event.Listener;

public class PluginListener extends PacketListenerAbstract implements Listener {
    private final ListenerType type;

    public PluginListener(ListenerType type) {
        this.type = type;
    }

    public PluginListener() {
        this.type = ListenerType.ALL;
    }

    public ListenerType getType() {
        return type;
    }
}
