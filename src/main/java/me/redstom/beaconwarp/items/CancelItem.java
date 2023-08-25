package me.redstom.beaconwarp.items;

import me.redstom.beaconwarp.common.TextConstants;
import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.Menu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Supplier;

public class CancelItem extends Item<Menu<?>> {
    private final Supplier<Menu<?>> previous;

    public CancelItem(Menu<?> menu,
                      Supplier<Menu<?>> previous) {
        super(menu, Material.RED_WOOL);

        this.previous = previous;

        init();
        update();
    }

    @Override
    protected void init() {
        item.setAction(this::onClick);
    }

    @Override
    protected ItemBuilder update(ItemBuilder item) {
        item.displayName(Component.text("Annuler")
                                  .color(TextConstants.RED)
                                  .decoration(TextDecoration.ITALIC, false), true);

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        Menu<?> menu;
        if ((menu = previous.get()) != null) {
            menu.open((Player) event.getWhoClicked());
        } else {
            event.getWhoClicked()
                 .closeInventory();
        }
    }
}
