package me.redstom.beaconwarp.events;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.papermc.paper.event.block.BeaconDeactivatedEvent;
import me.redstom.beaconwarp.orm.entities.Warp;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import me.redstom.beaconwarp.text.Colors;
import me.redstom.beaconwarp.text.Components;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Optional;

@Singleton
public class BeaconListener
        implements Listener {

    @Inject private Repositories repositories;

    @EventHandler public void onBeaconLightOff(BeaconDeactivatedEvent event) {
        if (event.getBeacon() == null) {
            return;
        }

        Optional<Warp> optionalWarp = repositories.warps().findWarpOn(event.getBeacon().getLocation());

        if (optionalWarp.isEmpty()) {
            return;
        }

        Warp warp = optionalWarp.get();
        warp.state(Warp.State.DISABLED);
        repositories.warps().update(warp);

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(warp.user().uniqueId());

        if (offlinePlayer.isOnline()) {
            Player player = (Player) offlinePlayer;

            player.sendMessage(Components.PREFIX.append(Component.translatable("info.beacon-deactivate.body")
                    .args(Component.text(warp.name()).color(Colors.DARK_BLUE))));
        }
    }
}
