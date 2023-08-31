package me.redstom.beaconwarp.items.manager.creation;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.manager.CreationMenu;
import me.redstom.beaconwarp.inventories.manager.EditionMenu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.orm.entities.Warp;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CreateItem
        extends Item<CreationMenu> {

    public CreateItem(CreationMenu menu) {
        super(menu, Material.GREEN_WOOL);

        init();
        update();
    }

    @Override protected void init() {
        item.setAction(this::onClick);
    }

    @Override protected ItemBuilder update(ItemBuilder item) {
        item.displayName(r(Component.translatable("menus.warp-create.title")));
        item.lore(r(Component.translatable("menus.warp-create.description")));

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        Warp warp = menu().repositories().warps().create(menu().user(), menu().location());

        new EditionMenu(menu().locale(), menu().repositories(), warp).open((Player) event.getWhoClicked());
    }
}
