package me.redstom.beaconwarp.items.manager.edition;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.manager.EditionMenu;
import me.redstom.beaconwarp.inventories.manager.IconSelectionMenu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.text.Colors;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class IconItem
        extends Item<EditionMenu> {

    public IconItem(EditionMenu menu) {
        super(menu, Material.BRUSH);

        init();
        update();
    }

    @Override protected void init() {
        item.setAction(this::onClick);
    }

    @Override protected ItemBuilder update(ItemBuilder item) {
        item.displayName(r(Component.translatable("menus.warp-edit.change-icon.title")));
        item.lore(r(Component.translatable("menus.warp-edit.change-icon.description")),
                r(Component.translatable("menus.warp-edit.change-icon.current-icon")
                        .args(Component.translatable(menu().warp().icon()).color(Colors.LIGHT_BLUE))));

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        new IconSelectionMenu(menu().locale(), menu().repositories(), menu().warp()).open(
                (Player) event.getWhoClicked());
    }
}
