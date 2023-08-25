package me.redstom.beaconwarp.items.manager.edition;

import me.redstom.beaconwarp.common.TextConstants;
import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.manager.EditionMenu;
import me.redstom.beaconwarp.inventories.manager.IconSelectionMenu;
import me.redstom.beaconwarp.items.Item;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class IconItem extends Item<EditionMenu> {

    public IconItem(EditionMenu menu) {
        super(menu, Material.BRUSH);

        init();
        update();
    }

    @Override
    protected void init() {
        item.setAction(this::onClick);
    }

    @Override
    protected ItemBuilder update(ItemBuilder item) {
        item.displayName(Component.text("Icône"));
        item.lore(Component.text("Changer l'icône du warp"),
                Component.text("Actuellement : ")
                         .append(Component.translatable(menu().warp()
                                                              .icon())
                                          .color(TextConstants.LIGHT_BLUE)));

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        new IconSelectionMenu(menu().repositories(), menu().warp()).open((Player) event.getWhoClicked());
    }
}
