package space.commandf1.capi.menu;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import space.commandf1.capi.factory.factories.CustomInventoryFactory;
import space.commandf1.capi.loader.APILoader;

public class Menu {
    private final Inventory inventory;

    public Menu(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addListener(MenuAction action) {
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent event) {
                if (event.getClickedInventory() == null) {
                    return;
                }
                if (event.getClickedInventory().equals(inventory)) {
                    action.run(new MenuController(event));
                }
            }
        }, APILoader.getPlugin());
    }

    public Menu(CustomInventoryFactory factory) {
        this.inventory = factory.build();
    }

    public Inventory getInventory() {
        return inventory;
    }
}
