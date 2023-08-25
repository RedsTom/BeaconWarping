package me.redstom.beaconwarp.inventories.list;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import lombok.Getter;
import me.redstom.beaconwarp.common.Paginator;
import me.redstom.beaconwarp.inventories.Menu;
import me.redstom.beaconwarp.items.list.ArrowItem;
import me.redstom.beaconwarp.items.list.PlayerItem;
import me.redstom.beaconwarp.orm.entities.User;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.List;

import static me.redstom.beaconwarp.common.TextConstants.*;

public class PlayerListMenu extends Menu<ChestGui> {

    private static final Component TITLE = SHORT_PREFIX.append(Component.text("Liste des joueurs")
                                                                        .color(NamedTextColor.DARK_GRAY));

    @Getter
    private final Repositories repositories;

    public PlayerListMenu(Repositories repositories) {
        super(new ChestGui(4, ComponentHolder.of(TITLE)));

        this.repositories = repositories;

        init();
    }

    @Override
    protected void init() {
        PaginatedPane players = new PaginatedPane(0, 0, 9, 3);

        Paginator<User> paginator = new Paginator<>(repositories.users()
                                                                .getAllWithWarp(), 3 * 9);

        List<OutlinePane> panes = paginator.generatePages(
                () -> new OutlinePane(0, 0, 9, 3),
                (pane, user) -> pane.addItem(new PlayerItem(this, user).item()));

        for (int i = 0; i < panes.size(); i++) {
            players.addPane(i, panes.get(i));
        }

        ArrowItem previous = new ArrowItem(this, ArrowItem.Direction.PREVIOUS, paginator, players);
        ArrowItem next = new ArrowItem(this, ArrowItem.Direction.NEXT, paginator, players);

        StaticPane pagination = new StaticPane(0, 3, 9, 1);
        pagination.addItem(previous.item(), 0, 0);
        pagination.addItem(next.item(), 8, 0);

        gui.addPane(players);
        gui.addPane(pagination);
    }

    @Override
    public void open(Player player) {
        if (!player.hasPermission(new Permission("beacon.warp"))) {
            player.sendMessage(PREFIX.append(Component.text("Vous n'avez pas la permission de vous téléporter aux warps des autres joueurs.")
                                                      .color(RED)));
            return;
        }

        super.open(player);
    }
}
