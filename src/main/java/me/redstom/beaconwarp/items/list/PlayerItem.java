package me.redstom.beaconwarp.items.list;

import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.Menu;
import me.redstom.beaconwarp.inventories.list.WarpListMenu;
import me.redstom.beaconwarp.items.Item;
import me.redstom.beaconwarp.orm.entities.User;
import me.redstom.beaconwarp.text.Colors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerItem
        extends Item<Menu<?>> {

    private final User user;

    public PlayerItem(Menu<?> menu, User user) {
        super(menu, Material.PLAYER_HEAD);

        this.user = user;

        init();
        update();
    }

    @Override protected void init() {
        item.setAction(this::onClick);
    }

    @Override protected ItemBuilder update(ItemBuilder item) {
        ItemStack is = item.toItemStack();
        SkullMeta sm = (SkullMeta) is.getItemMeta();

        OfflinePlayer player = Bukkit.getOfflinePlayer(user.uniqueId());
        sm.displayName(Component.text(player.getName() == null ? "Joueur inconnu" : player.getName())
                .color(TextColor.color(Colors.ORANGE))
                .decoration(TextDecoration.ITALIC, false));
        sm.setOwningPlayer(player);

        is.setItemMeta(sm);
        return new ItemBuilder(is);
    }

    private void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        new WarpListMenu(menu().repositories(), user).open(player);
    }
}
