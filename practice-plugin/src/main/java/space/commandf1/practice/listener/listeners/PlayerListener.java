package space.commandf1.practice.listener.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
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

        Player player = event.getPlayer();
        Location location = (Location) Settings.LOBBY.getObj();
        if (location != null) {
            player.teleport(location);
        } else if (player.hasPermission("practice.commands.admin")) {
            player.sendMessage("§cYou have not set the lobby location, please use \"/practice setLobby\" to set lobby location.");
        }
    }
}
