package me.redstom.beaconwarp.text;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Colors {

    public static final TextColor DARK_BLUE  = TextColor.color(55, 66, 250);
    public static final TextColor LIGHT_BLUE = TextColor.color(30, 144, 255);
    public static final TextColor RED        = TextColor.color(255, 71, 87);
    public static final TextColor GREEN      = TextColor.color(46, 213, 115);
    public static final TextColor ORANGE     = TextColor.color(255, 165, 2);

    public static final TextColor DARK_GRAY  = NamedTextColor.DARK_GRAY;
    public static final TextColor LIGHT_GRAY = NamedTextColor.GRAY;
    public static final TextColor WHITE      = NamedTextColor.WHITE;

    public static <T> TextColor colorIfSame(T expected, T actual) {
        return expected == actual ? Colors.GREEN : Colors.DARK_GRAY;
    }
}
