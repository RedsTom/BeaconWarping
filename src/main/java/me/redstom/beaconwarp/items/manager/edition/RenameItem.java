package me.redstom.beaconwarp.items.manager.edition;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.manager.EditionMenu;
import me.redstom.beaconwarp.inventories.manager.RenamingMenu;
import me.redstom.beaconwarp.items.Item;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import static me.redstom.beaconwarp.common.TextConstants.LIGHT_BLUE;

public class RenameItem extends Item<EditionMenu> {

    public RenameItem(EditionMenu menu) {
        super(menu, Material.NAME_TAG);

        init();
        update();
    }

    @Override
    public void init() {
        item.setAction(this::onClick);
    }

    @Override
    protected ItemBuilder update(ItemBuilder item) {
        item.displayName(Component.text("Renommer"));

        item.lore(
                Component.text("Changer le nom de votre warp."),
                Component.text("Nom actuel : ")
                         .append(Component.text(menu().warp()
                                                      .name())
                                          .color(LIGHT_BLUE)));

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        new RenamingMenu(menu().repositories(), menu().warp()).open((Player) event.getWhoClicked());
    }
}
