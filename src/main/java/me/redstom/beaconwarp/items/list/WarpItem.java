package me.redstom.beaconwarp.items.list;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.list.WarpListMenu;
import me.redstom.beaconwarp.inventories.list.WarpMenu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.orm.entities.Warp;
import me.redstom.beaconwarp.text.Colors;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class WarpItem
        extends Item<WarpListMenu> {

    private final Warp warp;

    public WarpItem(WarpListMenu menu, Warp warp) {
        super(menu, warp.icon());
        this.warp = warp;

        init();
        update();
    }

    @Override protected void init() {
        item.setAction(this::onClick);
    }

    @Override protected ItemBuilder update(ItemBuilder item) {
        item.displayName(Component.text(warp.name()));

        if (warp.state() == Warp.State.DISABLED) {
            item.lore(Component.translatable("menus.list.warps.disabled-item")
                    .color(Colors.RED));
        }

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        if (warp.state() != Warp.State.DISABLED) {
            new WarpMenu(menu().locale(), menu().repositories(), warp).open((Player) event.getWhoClicked());
        }
    }
}
