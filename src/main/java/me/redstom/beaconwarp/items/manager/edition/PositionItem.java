package me.redstom.beaconwarp.items.manager.edition;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.manager.EditionMenu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.orm.entities.Warp;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.List;

import static me.redstom.beaconwarp.text.Colors.colorIfSame;

public class PositionItem
        extends Item<EditionMenu> {

    public PositionItem(EditionMenu menu) {
        super(menu, Material.COMPASS);

        init();
        update();
    }

    @Override protected void init() {
        item.setAction(this::onClick);
    }

    @Override protected ItemBuilder update(ItemBuilder item) {
        item.displayName(Component.translatable("menus.warp-edit.position.title"));

        item.lore(Component.text("» ")
                        .append(Component.translatable("menus.warp-edit.position.top")
                                .color(colorIfSame(menu().warp().side(),
                                        Warp.Side.TOP))),
                Component.text("» ")
                        .append(Component.translatable("menus.warp-edit.position.north")
                                .color(colorIfSame(menu().warp().side(),
                                        Warp.Side.NORTH))),
                Component.text("» ")
                        .append(Component.translatable("menus.warp-edit.position.east")
                                .color(colorIfSame(menu().warp().side(),
                                        Warp.Side.EAST))),
                Component.text("» ")
                        .append(Component.translatable("menus.warp-edit.position.south")
                                .color(colorIfSame(menu().warp().side(),
                                        Warp.Side.SOUTH))),
                Component.text("» ")
                        .append(Component.translatable("menus.warp-edit.position.west")
                                .color(colorIfSame(menu().warp().side(),
                                        Warp.Side.WEST))));
        return item;
    }

    private void onClick(InventoryClickEvent event) {
        List<Warp.Side> values = Arrays.asList(Warp.Side.values());
        int             index  = values.indexOf(menu().warp().side());

        menu().warp().side(values.get((index + 1) % values.size()));
        menu().repositories().warps().update(menu().warp());

        update();
    }
}
