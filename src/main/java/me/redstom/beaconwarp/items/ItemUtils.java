package me.redstom.beaconwarp.items;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.redstom.beaconwarp.text.Colors;
import net.kyori.adventure.text.format.TextColor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemUtils {

    public static <T> TextColor colorIfSame(T expected, T actual) {
        return expected == actual ? Colors.GREEN : Colors.DARK_GRAY;
    }
}
