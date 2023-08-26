package me.redstom.beaconwarp.events;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.redstom.beaconwarp.orm.entities.User;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import me.redstom.beaconwarp.text.Components;
import me.redstom.beaconwarp.text.info.InfoMessages;
import me.redstom.beaconwarp.text.info.WarpExistenceNoticeTranslatableComponent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

@Singleton
public class BlockPlaceListener
        implements Listener {

    private static final Sound OK_SOUND     =
            Sound.sound(Key.key("entity.experience_orb.pickup"), Sound.Source.NEUTRAL, 1f, 1f);
    private static final Sound NOT_OK_SOUND = Sound.sound(Key.key("block.anvil.place"), Sound.Source.NEUTRAL, 1f, 1f);

    @Inject private Repositories repositories;
    @Inject private InfoMessages infoMessages;

    @EventHandler public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() != Material.BEACON) {
            return;
        }

        Player player = event.getPlayer();
        User   user   = repositories.users().getOrCreate(player.getUniqueId());

        if (user.informed()) {
            return;
        }

        Component message = infoMessages.warpExistenceNoticeMessage().build(args -> args
                .put(WarpExistenceNoticeTranslatableComponent.Parameters.CONTINUE_ACTION, audience -> informNextTime(user, audience))
                .put(WarpExistenceNoticeTranslatableComponent.Parameters.STOP_ACTION, audience -> stopInformingUser(user, audience)));

        player.sendMessage(message);
    }

    private void stopInformingUser(User user, Audience target) {
        user.informed(true);
        repositories.users().update(user);

        target.playSound(OK_SOUND, Sound.Emitter.self());
        target.sendMessage(Components.PREFIX.append(Component.text("Vous ne recevrez plus ces notifications !")));
    }

    private void informNextTime(User user, Audience target) {
        user.informed(false);
        repositories.users().update(user);

        target.playSound(NOT_OK_SOUND, Sound.Emitter.self());
        target.sendMessage(Components.PREFIX.append(
                Component.text("Un rappel vous sera envoyé la prochaine fois que vous " + "placerez une balise !")));
    }
}
