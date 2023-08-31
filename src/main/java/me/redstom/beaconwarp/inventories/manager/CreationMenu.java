package me.redstom.beaconwarp.inventories.manager;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.type.HopperGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import lombok.Getter;
import me.redstom.beaconwarp.inventories.Menu;
import me.redstom.beaconwarp.items.CancelItem;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.items.manager.creation.CreateItem;
import me.redstom.beaconwarp.orm.entities.User;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import me.redstom.beaconwarp.text.Colors;
import me.redstom.beaconwarp.text.Components;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Locale;

@Getter
public class CreationMenu
        extends Menu<HopperGui> {

    private static final Component TITLE = Components.SHORT_PREFIX.append(Component.translatable(
            "menus.warp-create.title"));

    private final Repositories repositories;

    private final User user;

    private final Location location;

    public CreationMenu(Locale locale, Repositories repositories, User user, Location location) {
        super(new HopperGui(ComponentHolder.of(TITLE)), locale);
        this.repositories = repositories;
        this.user         = user;
        this.location     = location;

        init();
    }

    public void init() {
        Item<?> create = new CreateItem(this);
        Item<?> cancel = new CancelItem(this, () -> null);

        StaticPane pane = new StaticPane(0, 0, 5, 1);
        pane.addItem(create.item(), 1, 0);
        pane.addItem(cancel.item(), 3, 0);

        gui.getSlotsComponent().addPane(pane);
    }

    @Override public void open(Player player) {
        if (!player.hasPermission("beacon.create")) {
            player.sendMessage(Components.PREFIX.append(Component.translatable("no-permissions.create")
                    .color(Colors.RED)));
            return;
        }

        super.open(player);
    }
}
