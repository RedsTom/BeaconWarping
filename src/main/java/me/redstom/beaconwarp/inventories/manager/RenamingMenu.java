package me.redstom.beaconwarp.inventories.manager;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.type.AnvilGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import lombok.Getter;
import me.redstom.beaconwarp.inventories.Menu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.items.manager.rename.BeaconItem;
import me.redstom.beaconwarp.items.manager.rename.ConfirmItem;
import me.redstom.beaconwarp.orm.entities.Warp;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import me.redstom.beaconwarp.text.Components;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicReference;

public class RenamingMenu
        extends Menu<AnvilGui> {

    private static final Component TITLE = Components.SHORT_PREFIX.append(Component.text("Renommer"));

    @Getter private final Repositories            repositories;
    @Getter private final Warp                    warp;
    @Getter private final AtomicReference<String> name;

    public RenamingMenu(Repositories repositories, Warp warp) {
        super(new AnvilGui(ComponentHolder.of(TITLE)));

        this.repositories = repositories;
        this.warp         = warp;

        this.name = new AtomicReference<>(warp.name());

        init();
    }

    @Override public void init() {
        Item<?> beacon;
        Item<?> confirm;
        beacon  = new BeaconItem(this);
        confirm = new ConfirmItem(this);

        StaticPane input = new StaticPane(0, 0, 1, 1);
        input.addItem(beacon.item(), 0, 0);

        StaticPane output = new StaticPane(0, 0, 1, 1);
        output.addItem(confirm.item(), 0, 0);

        gui.getFirstItemComponent().addPane(input);
        gui.getResultComponent().addPane(output);

        gui.setOnNameInputChanged(name::set);
        gui.setOnClose(event -> new EditionMenu(repositories, warp).open((Player) event.getPlayer()));
    }
}
