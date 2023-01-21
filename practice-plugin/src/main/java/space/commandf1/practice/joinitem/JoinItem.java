package space.commandf1.practice.joinitem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import space.commandf1.practice.cPracticePlugin;

public class JoinItem {
    private final ItemStack item;
    private int i = 0;
    private Action action;

    public JoinItem(ItemStack item) {
        this.item = item;
    }

    public void registerListeners() {
        cPracticePlugin.getInstance().getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onPlayerLeave(PlayerQuitEvent event) {
                event.getPlayer().getInventory().clear();
            }

            @EventHandler
            public void onPlayerJoin(PlayerJoinEvent event) {
                event.getPlayer().getInventory().setItem(i, item);
            }

            @EventHandler
            public void onPlayerInteract(PlayerInteractEvent event) {
                ItemStack itemStack = event.getItem();
                if (itemStack == null) {
                    return;
                }

                if (!itemStack.equals(item)) {
                    return;
                }

                action.run(event);
            }
        }, cPracticePlugin.getInstance());
    }

    public ItemStack getItem() {
        return item;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public interface Action {
        void run(PlayerInteractEvent event);
    }
}
