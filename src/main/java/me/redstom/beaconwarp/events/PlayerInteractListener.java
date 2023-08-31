package me.redstom.beaconwarp.events;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.redstom.beaconwarp.inventories.manager.CreationMenu;
import me.redstom.beaconwarp.inventories.manager.EditionMenu;
import me.redstom.beaconwarp.orm.entities.User;
import me.redstom.beaconwarp.orm.entities.Warp;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import me.redstom.beaconwarp.text.Colors;
import me.redstom.beaconwarp.text.Components;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Beacon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Optional;

@Singleton
public class PlayerInteractListener
        implements Listener {

    @Inject private Repositories repositories;

    @EventHandler public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getClickedBlock() == null) {
            return;
        }
        if (event.getClickedBlock().getType() != Material.BEACON) {
            return;
        }

        Player player = event.getPlayer();
        if (!player.isSneaking()) {
            return;
        }
        event.setCancelled(true);

        Beacon beacon = (Beacon) event.getClickedBlock().getState();
        if (beacon.getTier() < 1) {
            event.getPlayer()
                    .sendMessage(Components.PREFIX.append(Component.translatable("info.beacon-edit.must-be-activated")
                            .color(Colors.RED)));
            return;
        }

        Optional<Warp> warp = repositories.warps().findWarpOn(event.getClickedBlock().getLocation());
        if (warp.isEmpty()) {
            User user = repositories.users().getOrCreate(event.getPlayer().getUniqueId());
            new CreationMenu(repositories, user, event.getClickedBlock().getLocation()).open(player);
            return;
        }
        if (!warp.get().user().uniqueId().equals(player.getUniqueId()) && !player.hasPermission("beacon.admin")) {
            OfflinePlayer other = Bukkit.getOfflinePlayer(warp.get().user().uniqueId());

            player.sendMessage(Components.PREFIX.append(Component.translatable("info.beacon-edit.not-yours")
                    .color(Colors.RED)
                    .args(Components.playerName(other))));
            return;
        }

        new EditionMenu(repositories, warp.get()).open(player);
    }
}
