package me.redstom.beaconwarp.items.manager.rename;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.manager.RenamingMenu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.text.Colors;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

public class BeaconItem
        extends Item<RenamingMenu> {

    public BeaconItem(RenamingMenu menu) {
        super(menu, Material.BEACON);

        init();
        update();
    }

    @Override protected void init() {
        // Nothing to initiate
    }

    @Override protected ItemBuilder update(ItemBuilder item) {
        item.displayName(Component.text(menu().name().get()).color(Colors.WHITE));

        item.lore(r(Component.translatable("menus.warp-edit.change-name.current-name")
                        .args(Component.text(menu().warp().name()).color(Colors.LIGHT_BLUE))),
                Component.empty(),
                r(Component.translatable("menus.warp-edit.change-name.cancel")
                        .args(Component.text("ESC").color(Colors.LIGHT_BLUE))));
        return item;
    }
}
