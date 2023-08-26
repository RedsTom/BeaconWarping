package me.redstom.beaconwarp.inventories.list;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import lombok.Getter;
import me.redstom.beaconwarp.common.Paginator;
import me.redstom.beaconwarp.inventories.Menu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.items.ListItem;
import me.redstom.beaconwarp.items.list.ArrowItem;
import me.redstom.beaconwarp.items.list.WarpItem;
import me.redstom.beaconwarp.orm.entities.User;
import me.redstom.beaconwarp.orm.entities.Warp;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import me.redstom.beaconwarp.text.Components;
import net.kyori.adventure.text.Component;

import java.util.Comparator;
import java.util.List;


public class WarpListMenu
        extends Menu<ChestGui> {

    private static final Component TITLE = Components.SHORT_PREFIX.append(Component.text("Liste des warps"));

    @Getter private final Repositories repositories;
    private final         User         user;

    public WarpListMenu(Repositories repositories, User user) {
        super(new ChestGui(4, ComponentHolder.of(TITLE)));

        this.repositories = repositories;
        this.user         = user;

        init();
    }

    @Override protected void init() {
        PaginatedPane warps     = new PaginatedPane(0, 0, 9, 3);
        List<Warp>    userWarps = user.warps();
        userWarps.sort(Comparator.comparingInt(a -> a.icon().ordinal()));
        Paginator<Warp> paginator = new Paginator<>(userWarps, 3 * 9);

        List<OutlinePane> panes = paginator.generatePages(() -> new OutlinePane(0, 0, 9, 3),
                (pane, warp) -> pane.addItem(new WarpItem(this, warp).item()));

        for (int i = 0 ; i < panes.size() ; i++) {
            warps.addPane(i, panes.get(i));
        }

        Item<?> previous   = new ArrowItem(this, ArrowItem.Direction.PREVIOUS, paginator, warps);
        Item<?> next       = new ArrowItem(this, ArrowItem.Direction.NEXT, paginator, warps);
        Item<?> playerList = new ListItem(this);

        StaticPane pagination = new StaticPane(0, 3, 9, 1);
        pagination.addItem(previous.item(), 0, 0);
        pagination.addItem(next.item(), 8, 0);

        pagination.addItem(playerList.item(), 4, 0);

        gui.addPane(warps);
        gui.addPane(pagination);
    }
}
