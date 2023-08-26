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
        item.displayName(Component.text("Supprimer"));
        item.lore(Component.text("Supprimer le warp."),
                Component.text("Cette action est irréversible, aucune confirmation ne vous sera demandée !"),
                Component.empty(),
                Component.text()
                        .append(Component.text("Clic droit").color(Colors.LIGHT_BLUE))
                        .appendSpace()
                        .append(Component.text("pour confirmer"))
                        .build());

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        if (!event.getClick().isRightClick()) {
            return;
        }

        menu().repositories().warps().delete(menu().warp());
        event.getWhoClicked().closeInventory();
        event.getWhoClicked().sendMessage(Components.PREFIX.append(Component.text("Le warp a été supprimé").color(Colors.RED)));
    }
}
