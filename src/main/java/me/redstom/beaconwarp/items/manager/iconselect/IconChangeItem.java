package me.redstom.beaconwarp.items.manager.iconselect;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.manager.EditionMenu;
import me.redstom.beaconwarp.inventories.manager.IconSelectionMenu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.text.Colors;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class IconChangeItem
        extends Item<IconSelectionMenu> {

    private final Material icon;

    public IconChangeItem(IconSelectionMenu menu, Material icon) {
        super(menu, icon);
        this.icon = icon;

        init();
        update();
    }

    @Override protected void init() {
        item.setAction(this::onClick);
    }

    @Override protected ItemBuilder update(ItemBuilder item) {
        item.displayName(Component.translatable(icon).color(Colors.LIGHT_BLUE));

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        menu().warp().icon(icon);
        menu().repositories().warps().update(menu().warp());

        new EditionMenu(menu().repositories(), menu().warp()).open((Player) event.getWhoClicked());
    }
}
