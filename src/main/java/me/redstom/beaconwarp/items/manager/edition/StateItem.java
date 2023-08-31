package me.redstom.beaconwarp.items.manager.edition;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.manager.EditionMenu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.orm.entities.Warp;
import me.redstom.beaconwarp.text.Styles;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.List;

import static me.redstom.beaconwarp.text.Colors.colorIfSame;

public class StateItem
        extends Item<EditionMenu> {

    public StateItem(EditionMenu menu) {
        super(menu, Material.TRIPWIRE_HOOK);

        init();
        update();
    }

    @Override protected void init() {
        item.setAction(this::onClick);
    }

    @Override protected ItemBuilder update(ItemBuilder item) {

        item.displayName(Component.translatable("menus.warp-edit.state.title").style(Styles.ITEM_NAME_STYLE));
        item.lore(Component.text("» ")
                        .append(Component.translatable("menus.warp-edit.state.enabled")
                                .color(colorIfSame(menu().warp().state(), Warp.State.ENABLED))),
                Component.text("» ")
                        .append(Component.translatable("menus.warp-edit.state.disabled")
                                .color(colorIfSame(menu().warp().state(), Warp.State.DISABLED))));

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        List<Warp.State> values = Arrays.asList(Warp.State.values());
        int              index  = values.indexOf(menu().warp().state());

        menu().warp().state(values.get((index + 1) % values.size()));
        menu().repositories().warps().update(menu().warp());

        update();
    }
}
