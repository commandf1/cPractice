package space.commandf1.practice.listener.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import space.commandf1.practice.listener.ListenerType;
import space.commandf1.practice.listener.PluginListener;
import space.commandf1.practice.settings.Settings;

public class PlayerListener extends PluginListener {
    public PlayerListener() {
        super(ListenerType.BUKKIT);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if ((boolean) Settings.NO_JOIN_LEAVE_MESSAGE.getObj()) {
            event.setQuitMessage(null);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if ((boolean) Settings.NO_JOIN_LEAVE_MESSAGE.getObj()) {
            event.setJoinMessage(null);
        }
    }
}
