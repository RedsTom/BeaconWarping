package me.redstom.beaconwarp.inventories.list;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.type.HopperGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import lombok.Getter;
import me.redstom.beaconwarp.inventories.Menu;
import me.redstom.beaconwarp.items.CancelItem;
import me.redstom.beaconwarp.items.list.TeleportItem;
import me.redstom.beaconwarp.orm.entities.Warp;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import me.redstom.beaconwarp.text.Components;
import net.kyori.adventure.text.Component;

@Getter
public class WarpMenu
        extends Menu<HopperGui> {

    private static final Component TITLE = Components.SHORT_PREFIX.append(Component.text("Warp"));

    private final Repositories repositories;

    private final Warp warp;

    public WarpMenu(Repositories repositories, Warp warp) {
        super(new HopperGui(ComponentHolder.of(TITLE)));

        this.repositories = repositories;
        this.warp         = warp;

        init();
    }

    @Override public void init() {
        TeleportItem teleport = new TeleportItem(this);
        CancelItem   cancel   = new CancelItem(this, () -> new WarpListMenu(repositories, warp.user()));

        StaticPane pane = new StaticPane(0, 0, 5, 1);
        pane.addItem(teleport.item(), 1, 0);
        pane.addItem(cancel.item(), 3, 0);

        gui.getSlotsComponent().addPane(pane);
    }
}
