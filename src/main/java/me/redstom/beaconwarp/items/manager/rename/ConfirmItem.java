package me.redstom.beaconwarp.items.manager.rename;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.manager.RenamingMenu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.text.Colors;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import static me.redstom.beaconwarp.text.Styles.ITEM_NAME_STYLE;

public class ConfirmItem
        extends Item<RenamingMenu> {

    public ConfirmItem(RenamingMenu menu) {
        super(menu, Material.GREEN_WOOL);

        init();
        update();
    }

    @Override protected void init() {
        item.setAction(this::onClick);
    }

    @Override protected ItemBuilder update(ItemBuilder item) {
        item.displayName(Component.translatable("confirm").style(ITEM_NAME_STYLE).color(Colors.GREEN));

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        event.setCancelled(true);

        menu().warp().name(menu().name().get());
        menu().repositories().warps().update(menu().warp());

        event.getWhoClicked().closeInventory();
    }
}
