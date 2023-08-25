package me.redstom.beaconwarp.events;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.redstom.beaconwarp.common.TextConstants;
import me.redstom.beaconwarp.orm.entities.User;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static me.redstom.beaconwarp.common.TextConstants.*;

@Singleton
public class BlockPlaceListener implements Listener {

    private static final Sound OK_SOUND = Sound.sound(Key.key("entity.experience_orb.pickup"), Sound.Source.NEUTRAL, 1f, 1f);
    private static final Sound NOT_OK_SOUND = Sound.sound(Key.key("block.anvil.place"), Sound.Source.NEUTRAL, 1f, 1f);

    @Inject private Repositories repositories;

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced()
                 .getType() != Material.BEACON) {
            return;
        }

        Player player = event.getPlayer();
        User user = repositories.users().getOrCreate(player.getUniqueId());

        if (user.informed()) {
            return;
        }

        ComponentLike message =
                TextConstants.PREFIX
                        .append(Component.text("Le saviez-vous ? En faisant clic droit lorsque vous êtes accroupis sur cette " +
                                "balise, vous pouvez configurer un warp auquel les autres joueurs pourront se téléporter via " +
                                "la commande "))
                        .append(Component.text("/pwarps")
                                         .color(DARK_BLUE)
                                         .hoverEvent(HoverEvent.showText(Component.text("Cliquer pour suggérer")))
                                         .clickEvent(ClickEvent.suggestCommand("/pwarps")))
                        .append(Component.text("."))
                        .appendNewline()
                        .append(Component.text("[J'ai compris]")
                                         .color(GREEN)
                                         .clickEvent(ClickEvent.callback(audience -> stopInformingUser(user, audience))))
                        .appendSpace()
                        .append(Component.text("[Me le rappeler]")
                                         .color(ORANGE)
                                         .clickEvent(ClickEvent.callback(audience -> informNextTime(user, audience))));
        player.sendMessage(message);

    }

    private void stopInformingUser(User user,
                                   Audience target) {
        user.informed(true);
        repositories.users().update(user);

        target.playSound(OK_SOUND, Sound.Emitter.self());
        target.sendMessage(PREFIX.append(Component.text("Vous ne recevrez plus ces notifications !")));
    }

    private void informNextTime(User user,
                                Audience target) {
        user.informed(false);
        repositories.users().update(user);

        target.playSound(NOT_OK_SOUND, Sound.Emitter.self());
        target.sendMessage(PREFIX.append(Component.text("Un rappel vous sera envoyé la prochaine fois que vous " +
                "placerez une balise !")));
    }
}
