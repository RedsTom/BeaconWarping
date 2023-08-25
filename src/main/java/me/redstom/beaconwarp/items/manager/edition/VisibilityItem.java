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

import static me.redstom.beaconwarp.common.TextConstants.ITEM_NAME_STYLE;
import static me.redstom.beaconwarp.items.ItemUtils.colorIfSame;

public class VisibilityItem extends Item<EditionMenu> {

    public VisibilityItem(EditionMenu menu) {
        super(menu, Material.TRIPWIRE_HOOK);

        init();
        update();
    }

    @Override
    protected void init() {
        item.setAction(this::onClick);
    }

    @Override
    protected ItemBuilder update(ItemBuilder item) {

        item.displayName(Component.text("Visibilité")
                                  .style(ITEM_NAME_STYLE));
        item.lore(Component.text("» ")
                           .append(Component.text("Activé")
                                            .color(colorIfSame(menu().warp().state(), Warp.State.ENABLED))),
                Component.text("» ")
                         .append(Component.text("Désactivé")
                                          .color(colorIfSame(menu().warp().state(), Warp.State.DISABLED))));

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        List<Warp.State> values = Arrays.asList(Warp.State.values());
        int index = values.indexOf(menu().warp().state());

        menu().warp().state(values.get((index + 1) % values.size()));
        menu().repositories().warps().update(menu().warp());

        update();
    }
}
