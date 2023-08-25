package me.redstom.beaconwarp.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TextConstants {

    public static final TextColor DARK_BLUE  = TextColor.color(55, 66, 250);
    public static final TextColor LIGHT_BLUE = TextColor.color(30, 144, 255);
    public static final TextColor RED        = TextColor.color(255, 71, 87);
    public static final TextColor GREEN      = TextColor.color(46, 213, 115);
    public static final TextColor ORANGE     = TextColor.color(255, 165, 2);

    public static final Style ITEM_NAME_STYLE = Style.style(GREEN).decoration(TextDecoration.ITALIC, false);
    public static final Style ITEM_LORE_STYLE =
            Style.style(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

    public static final Component SHORT_PREFIX = Component.empty()
            .append(Component.text("B").color(DARK_BLUE))
            .append(Component.text("W").color(LIGHT_BLUE))
            .appendSpace()
            .append(Component.text("»").color(NamedTextColor.WHITE))
            .appendSpace()
            .color(NamedTextColor.DARK_GRAY);

    public static final Component PREFIX = Component.empty()
            .append(Component.text("Beacon").color(DARK_BLUE))
            .append(Component.text("Warp").color(LIGHT_BLUE))
            .appendSpace()
            .append(Component.text("»").color(NamedTextColor.WHITE))
            .appendSpace()
            .color(NamedTextColor.GRAY);
}
