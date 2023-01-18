package space.commandf1.capi.menu;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public class MenuController {
    private final InventoryClickEvent event;

    MenuController(InventoryClickEvent event) {
        this.event = event;
    }

    public void setCancelled(boolean b) {
        this.event.setCancelled(b);
    }

    public Inventory getClickedInventory() {
        return this.event.getClickedInventory();
    }

    public InventoryType.SlotType getSlotType() {
        return this.event.getSlotType();
    }

    public ItemStack getCursor() {
        return this.event.getCursor();
    }

    public ItemStack getCurrentItem() {
        return this.event.getCurrentItem();
    }

    public boolean isRightClick() {
        return this.event.isRightClick();
    }

    public boolean isLeftClick() {
        return this.event.isLeftClick();
    }

    public boolean isShiftClick() {
        return this.event.isShiftClick();
    }

    public void setCurrentItem(ItemStack stack) {
        this.event.setCurrentItem(stack);
    }

    public int getSlot() {
        return this.event.getSlot();
    }

    public int getRawSlot() {
        return this.event.getRawSlot();
    }

    public int getHotbarButton() {
        return this.event.getHotbarButton();
    }

    public InventoryAction getAction() {
        return this.event.getAction();
    }

    public ClickType getClick() {
        return this.event.getClick();
    }
}
