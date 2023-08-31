package me.redstom.beaconwarp.inventories.manager;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.type.DropperGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import lombok.Getter;
import me.redstom.beaconwarp.inventories.Menu;
import me.redstom.beaconwarp.items.manager.iconselect.IconChangeItem;
import me.redstom.beaconwarp.orm.entities.Warp;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import me.redstom.beaconwarp.text.Components;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Locale;

@Getter
public class IconSelectionMenu
        extends Menu<DropperGui> {

    private static final Component  TITLE =
            Components.SHORT_PREFIX.append(Component.translatable("menus.warp-edit.change-icon.title"));
    private static final Material[] ICONS =
            new Material[]{ Material.COMPASS, Material.RECOVERY_COMPASS, Material.GOLD_INGOT, Material.EMERALD,
                            Material.REDSTONE, Material.MINECART, Material.OAK_BOAT, Material.RED_BED,
                            Material.ENDER_PEARL };

    private final Repositories repositories;

    private final Warp warp;

    public IconSelectionMenu(Locale locale, Repositories repositories, Warp warp) {
        super(new DropperGui(ComponentHolder.of(TITLE)), locale);

        this.repositories = repositories;
        this.warp         = warp;

        init();
    }

    @Override protected void init() {
        OutlinePane icons = new OutlinePane(0, 0, 3, 3);

        for (Material icon : ICONS) {
            icons.addItem(new IconChangeItem(this, icon).item());
        }

        gui.getContentsComponent().addPane(icons);
        gui.setOnClose(event -> new EditionMenu(locale, repositories, warp).open((Player) event.getPlayer()));
    }
}
