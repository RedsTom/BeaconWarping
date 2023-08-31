package me.redstom.beaconwarp.items;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.Menu;
import me.redstom.beaconwarp.inventories.list.PlayerListMenu;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListItem
        extends Item<Menu<?>> {

    public ListItem(Menu<?> menu) {
        super(menu, Material.FILLED_MAP);

        init();
        update();
    }

    @Override protected void init() {
        item.setAction(this::onClick);
    }

    @Override protected ItemBuilder update(ItemBuilder item) {
        item.displayName(r(Component.translatable("menus.list.warps.title")));
        item.lore(r(Component.translatable("menus.list.warps.description")));

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        new PlayerListMenu(menu().locale(), menu().repositories()).open((Player) event.getWhoClicked());
    }
}
