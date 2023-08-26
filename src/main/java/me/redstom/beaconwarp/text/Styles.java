package me.redstom.beaconwarp.text;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Styles {

    public static final Style ITEM_NAME_STYLE = Style.style(Colors.GREEN).decoration(TextDecoration.ITALIC, false);
    public static final Style ITEM_LORE_STYLE =
            Style.style(Colors.LIGHT_GRAY).decoration(TextDecoration.ITALIC, false);
}
