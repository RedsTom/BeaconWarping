package me.redstom.beaconwarp.text;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Components {

    public static final Component SHORT_PREFIX = Component.empty()
            .append(Component.text("B").color(Colors.DARK_BLUE))
            .append(Component.text("W").color(Colors.LIGHT_BLUE))
            .appendSpace()
            .append(Component.text("»").color(Colors.WHITE))
            .appendSpace()
            .color(Colors.DARK_GRAY);

    public static final Component PREFIX = Component.empty()
            .append(Component.text("Beacon").color(Colors.DARK_BLUE))
            .append(Component.text("Warp").color(Colors.LIGHT_BLUE))
            .appendSpace()
            .append(Component.text("»").color(Colors.WHITE))
            .appendSpace()
            .color(Colors.LIGHT_GRAY);

    public static Component playerName(OfflinePlayer player) {
        return Component.empty()
                .append(player.getName() == null ? Component.translatable("unknown-player") :
                        Component.text(player.getName()))
                .color(Colors.LIGHT_BLUE);
    }
}
