package me.redstom.beaconwarp.items.list;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.list.WarpMenu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.orm.entities.Warp;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TeleportItem
        extends Item<WarpMenu> {

    public TeleportItem(WarpMenu menu) {
        super(menu, Material.ENDER_PEARL);

        init();
        update();
    }

    @Override protected void init() {
        item.setAction(this::onClick);
    }

    @Override protected ItemBuilder update(ItemBuilder item) {
        item.displayName(r(Component.translatable("menus.list.warp.teleport")));

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        Warp warp = menu().warp();

        World    world = Bukkit.getWorld(warp.world());
        Location loc   = warp.side().apply(new Location(world, warp.x(), warp.y(), warp.z()));
        loc.add(.5, 0, .5);

        event.getWhoClicked().teleport(loc);
    }
}
