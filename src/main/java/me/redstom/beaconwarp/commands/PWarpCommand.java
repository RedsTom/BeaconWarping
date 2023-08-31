package me.redstom.beaconwarp.commands;

import com.google.inject.Inject;
import me.redstom.beaconwarp.inventories.list.PlayerListMenu;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import me.redstom.beaconwarp.text.Colors;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PWarpCommand
        implements CommandExecutor {

    @Inject private Repositories repositories;

    @Override public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
                                       @NotNull String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("La console ne peut pas exécuter cette commande !").color(Colors.RED));
            return false;
        }

        new PlayerListMenu(player.locale(), repositories).open(player);

        return true;
    }
}
