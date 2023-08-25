package me.redstom.beaconwarp.items.manager.rename;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.manager.RenamingMenu;
import me.redstom.beaconwarp.items.Item;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;

import static me.redstom.beaconwarp.common.TextConstants.*;

public class BeaconItem extends Item<RenamingMenu> {

    public BeaconItem(RenamingMenu menu) {
        super(menu, Material.BEACON);

        init();
        update();
    }

    @Override
    protected void init() {
        // Nothing to initiate
    }

    @Override
    protected ItemBuilder update(ItemBuilder item) {
        item.displayName(Component.text(menu().name()
                                              .get())
                                  .style(ITEM_NAME_STYLE)
                                  .color(NamedTextColor.WHITE));

        item.lore(Component.text("Nom actuel")
                           .style(ITEM_LORE_STYLE),
                Component.empty(),
                Component.text()
                         .append(Component.text("ESC")
                                          .color(LIGHT_BLUE))
                         .append(Component.text(" pour annuler"))
                         .style(ITEM_LORE_STYLE)
                         .build()
        );
        return item;
    }
}
