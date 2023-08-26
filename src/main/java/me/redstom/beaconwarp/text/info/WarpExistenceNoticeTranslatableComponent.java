package me.redstom.beaconwarp.text.info;

import com.google.inject.Singleton;
import lombok.experimental.UtilityClass;
import me.redstom.beaconwarp.text.Colors;
import me.redstom.beaconwarp.text.Components;
import me.redstom.beaconwarp.text.utils.TranslatableComponent;
import me.redstom.beaconwarp.text.utils.ComponentArg;
import me.redstom.beaconwarp.text.utils.ComponentArgs;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;

@Singleton
public class WarpExistenceNoticeTranslatableComponent
        extends TranslatableComponent {

    @Override public Component build(ComponentArgs args) {
        Callback stopAction     = args.get(Parameters.STOP_ACTION);
        Callback continueAction = args.get(Parameters.CONTINUE_ACTION);

        return Components.PREFIX.append(Component.translatable("info.beacon-place.body"))
                .appendSpace()
                .append(Component.text("/pwarps")
                        .color(Colors.DARK_BLUE)
                        .hoverEvent(HoverEvent.showText(Component.translatable("info.beacon-place.buttons" +
                                                                               ".click-to-suggest")))
                        .clickEvent(ClickEvent.suggestCommand("/pwarps")))
                .append(Component.text("."))
                .appendNewline()
                .append(Component.translatable("info.beacon-place.buttons.ok")
                        .color(Colors.GREEN)
                        .clickEvent(ClickEvent.callback(stopAction::run)))
                .appendSpace()
                .append(Component.translatable("info.beacon-place.buttons.remind-me")
                        .color(Colors.ORANGE)
                        .clickEvent(ClickEvent.callback(continueAction::run)));
    }

    @UtilityClass
    public class Parameters {

        public final ComponentArg<Callback> CONTINUE_ACTION = new ComponentArg<>(Callback.class);
        public final ComponentArg<Callback> STOP_ACTION     = new ComponentArg<>(Callback.class);
    }

    @FunctionalInterface
    public interface Callback {

        void run(Audience audience);
    }
}
