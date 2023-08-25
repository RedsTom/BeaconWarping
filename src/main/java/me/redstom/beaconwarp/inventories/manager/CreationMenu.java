package me.redstom.beaconwarp.inventories.manager;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.type.HopperGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import lombok.Getter;
import me.redstom.beaconwarp.common.TextConstants;
import me.redstom.beaconwarp.inventories.Menu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.items.CancelItem;
import me.redstom.beaconwarp.items.manager.creation.CreateItem;
import me.redstom.beaconwarp.orm.entities.User;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static me.redstom.beaconwarp.common.TextConstants.PREFIX;
import static me.redstom.beaconwarp.common.TextConstants.SHORT_PREFIX;

public class CreationMenu extends Menu<HopperGui> {

    private static final Component TITLE = SHORT_PREFIX.append(Component.text("Créer un warp"));

    @Getter
    private final Repositories repositories;

    @Getter
    private final User user;

    @Getter
    private final Location location;

    public CreationMenu(Repositories repositories,
                        User user,
                        Location location) {
        super(new HopperGui(ComponentHolder.of(TITLE)));
        this.repositories = repositories;
        this.user = user;
        this.location = location;

        init();
    }

    public void init() {
        Item<?> cancel;
        Item<?> create;
        create = new CreateItem(this);
        cancel = new CancelItem(this, () -> null);

        StaticPane pane = new StaticPane(0, 0, 5, 1);
        pane.addItem(create.item(), 1, 0);
        pane.addItem(cancel.item(), 3, 0);

        gui.getSlotsComponent()
           .addPane(pane);
    }

    @Override
    public void open(Player player) {
        if (player.hasPermission("beacon.create")) {
            super.open(player);
            return;
        }

        player.closeInventory();
        player.sendMessage(PREFIX.append(Component.text("Vous n'avez pas la permission de créer un warp !")
                                                  .color(TextConstants.RED)));
    }

}
