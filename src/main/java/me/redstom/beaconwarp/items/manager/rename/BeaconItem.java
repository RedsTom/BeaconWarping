package me.redstom.beaconwarp.items.manager.rename;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.manager.RenamingMenu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.text.Colors;
import me.redstom.beaconwarp.text.Styles;
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
        item.displayName(Component.text(menu().name().get()).style(Styles.ITEM_NAME_STYLE).color(Colors.WHITE));

        item.lore(Component.text("Nom actuel").style(Styles.ITEM_LORE_STYLE),
                Component.empty(),
                Component.text()
                        .append(Component.text("ESC").color(Colors.LIGHT_BLUE))
                        .append(Component.text(" pour " + "annuler"))
                        .style(Styles.ITEM_LORE_STYLE)
                        .build());
        return item;
    }
}
