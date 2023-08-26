package me.redstom.beaconwarp.text;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kyori.adventure.text.Component;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Components {

    public static final Component SHORT_PREFIX = Component.empty()
            .append(Component.text("B").color(Colors.DARK_BLUE))
            .append(Component.text("W").color(Colors.LIGHT_BLUE))
            .appendSpace()
            .append(Component.text("»").color(Colors.WHITE))
            .appendSpace()
            .color(Colors.DARK_GRAY);
            .append(Component.text("»").color(NamedTextColor.WHITE))
            .appendSpace()
            .color(NamedTextColor.DARK_GRAY);

    public static final Component PREFIX = Component.empty()
            .append(Component.text("Beacon").color(Colors.DARK_BLUE))
            .append(Component.text("Warp").color(Colors.LIGHT_BLUE))
            .appendSpace()
            .append(Component.text("»").color(Colors.WHITE))
            .appendSpace()
            .color(Colors.LIGHT_GRAY);
}
