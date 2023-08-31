package me.redstom.beaconwarp.items.list;

import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import lombok.RequiredArgsConstructor;
import me.redstom.beaconwarp.common.Paginator;
import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.Menu;
import me.redstom.beaconwarp.items.Item;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ArrowItem
        extends Item<Menu<?>> {

    private final Direction     direction;
    private final Paginator<?>  paginator;
    private final PaginatedPane pane;

    public ArrowItem(Menu<?> menu, Direction direction, Paginator<?> paginator, PaginatedPane pane) {
        super(menu, Material.ARROW);

        this.direction = direction;
        this.paginator = paginator;
        this.pane      = pane;

        init();
        update();
    }

    @Override protected void init() {
        item.setAction(this::onClick);
    }

    @Override protected ItemBuilder update(ItemBuilder item) {
        item.displayName(r(Component.translatable(direction.translationkey)));

        return item;
    }

    private void onClick(InventoryClickEvent event) {
        int offset = paginator.offset(pane.getPage(), direction.offset);
        pane.setPage(offset);
        menu().update();
    }

    @RequiredArgsConstructor
    public enum Direction {
        PREVIOUS(-1, "menus.list.previous"),
        NEXT(1, "menus.list.next");

        private final int offset;
        private final String translationkey;
    }
}
