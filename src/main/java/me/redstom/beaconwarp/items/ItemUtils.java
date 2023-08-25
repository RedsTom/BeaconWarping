package me.redstom.beaconwarp.items;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

import static me.redstom.beaconwarp.common.TextConstants.GREEN;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemUtils {

    public static <T> TextColor colorIfSame(T expected,
                                       T actual) {
        return expected == actual
                ? GREEN
                : NamedTextColor.DARK_GRAY;
    }
}
