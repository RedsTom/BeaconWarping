package me.redstom.beaconwarp.items.manager.edition;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.manager.EditionMenu;
import me.redstom.beaconwarp.inventories.manager.RenamingMenu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.text.Colors;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RenameItem
        extends Item<EditionMenu> {

    public RenameItem(EditionMenu menu) {
        super(menu, Material.NAME_TAG);

        init();
        update();
    }

    @Override public void init() {
        item.setAction(this::onClick);
    }

    @Override protected ItemBuilder update(ItemBuilder item) {
        item.displayName(Component.translatable("menus.warp-edit.change-name.title"));

        item.lore(Component.translatable("menus.warp-edit.change-name.description"),
                Component.empty(),
                Component.translatable("menus.warp-edit.change-name.current-name")
                        .args(Component.text(menu().warp().name()).color(Colors.LIGHT_BLUE)));

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        new RenamingMenu(menu().locale(), menu().repositories(), menu().warp()).open((Player) event.getWhoClicked());
    }
}
