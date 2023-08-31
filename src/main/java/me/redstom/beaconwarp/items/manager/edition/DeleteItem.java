package me.redstom.beaconwarp.items.manager.edition;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.manager.EditionMenu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.text.Colors;
import me.redstom.beaconwarp.text.Components;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class DeleteItem
        extends Item<EditionMenu> {

    public DeleteItem(EditionMenu menu) {
        super(menu, Material.BARRIER);

        init();
        update();
    }

    @Override protected void init() {
        item.setAction(this::onClick);
    }

    @Override protected ItemBuilder update(ItemBuilder item) {
        item.displayName(r(Component.translatable("menus.warp-edit.delete.title")));
        item.lore(r(Component.translatable("menus.warp-edit.delete.description")),
                r(Component.translatable("menus.warp-edit.delete.warning")),
                Component.empty(),
                r(Component.translatable("menus.warp-edit.delete.confirm")));

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        if (!event.getClick().isRightClick()) {
            return;
        }

        menu().repositories().warps().delete(menu().warp());
        event.getWhoClicked().closeInventory();
        event.getWhoClicked()
                .sendMessage(Components.PREFIX.append(
                        Component.translatable("menus.warp-edit.delete.done").color(Colors.RED)));
    }
}
