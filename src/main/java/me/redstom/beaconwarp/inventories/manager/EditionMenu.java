package me.redstom.beaconwarp.inventories.manager;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import lombok.Getter;
import me.redstom.beaconwarp.inventories.Menu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.items.ListItem;
import me.redstom.beaconwarp.items.manager.edition.*;
import me.redstom.beaconwarp.orm.entities.Warp;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import me.redstom.beaconwarp.text.Components;
import net.kyori.adventure.text.Component;

import java.util.Locale;

public class EditionMenu
        extends Menu<ChestGui> {

    private static final Component TITLE = Components.SHORT_PREFIX.append(Component.translatable(
            "menus.warp-edit.title"));

    @Getter private final Warp         warp;
    @Getter private final Repositories repositories;

    private Item<?> rename;
    private Item<?> position;
    private Item<?> visibility;

    public EditionMenu(Locale locale, Repositories repositories, Warp warp) {
        super(new ChestGui(5, ComponentHolder.of(TITLE)), locale);

        this.repositories = repositories;
        this.warp         = warp;

        init();
    }

    @Override public void init() {
        this.rename     = new RenameItem(this);
        this.position   = new PositionItem(this);
        this.visibility = new StateItem(this);
        Item<?> icon   = new IconItem(this);
        Item<?> delete = new DeleteItem(this);
        Item<?> list   = new ListItem(this);

        StaticPane pane = new StaticPane(2, 1, 5, 3);
        pane.addItem(rename.item(), 0, 0);
        pane.addItem(position.item(), 2, 0);
        pane.addItem(visibility.item(), 4, 0);
        pane.addItem(icon.item(), 0, 2);
        pane.addItem(delete.item(), 2, 2);
        pane.addItem(list.item(), 4, 2);

        gui.addPane(pane);
    }

    @Override public void update() {
        rename.update();
        position.update();
        visibility.update();
    }
}

