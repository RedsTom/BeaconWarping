package me.redstom.beaconwarp.events;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.redstom.beaconwarp.orm.entities.Warp;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import me.redstom.beaconwarp.text.Colors;
import me.redstom.beaconwarp.text.Components;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Optional;

@Singleton
public class BlockBreakListener
        implements Listener {

    @Inject private Repositories repositories;

    @EventHandler public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.BEACON) {
            return;
        }

        Optional<Warp> warp = repositories.warps().findWarpOn(event.getBlock().getLocation());
        if (warp.isEmpty()) {
            return;
        }

        Player player = event.getPlayer();
        if (!warp.get().user().uniqueId().equals(player.getUniqueId()) && !player.hasPermission("beacon.admin")) {
            event.setCancelled(true);

            OfflinePlayer other = Bukkit.getOfflinePlayer(warp.get().user().uniqueId());
            player.sendMessage(Components.PREFIX.append(Component.translatable("info.beacon-break.not-yours")
                    .color(Colors.RED)
                    .args(Components.playerName(other))));

            return;
        }

        repositories.warps().delete(warp.get());
        player.sendMessage(Components.PREFIX.append(Component.translatable("info.beacon-break.ok")
                .args(Component.text(warp.get().name()).color(Colors.DARK_BLUE))));
    }
}
