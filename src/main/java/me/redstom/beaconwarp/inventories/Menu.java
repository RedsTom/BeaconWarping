package me.redstom.beaconwarp.inventories;

import com.github.stefvanschie.inventoryframework.gui.type.util.Gui;
import lombok.Getter;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import org.bukkit.entity.Player;

import java.util.Locale;

@Getter
public abstract class Menu <T extends Gui> {

    protected final T      gui;
    protected final Locale locale;

    protected Menu(T gui, Locale locale) {
        this.gui = gui;
        this.locale = locale;

        gui.setOnGlobalClick(event -> event.setCancelled(true));
        gui.setOnGlobalDrag(event -> event.setCancelled(true));
    }

    protected abstract void init();

    public void update() {
        gui.update();
    }

    public void open(Player player) {
        this.gui.show(player);
    }

    public abstract Repositories repositories();
}
