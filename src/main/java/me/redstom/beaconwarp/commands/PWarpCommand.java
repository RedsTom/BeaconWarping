package me.redstom.beaconwarp.commands;

import com.google.inject.Inject;
import me.redstom.beaconwarp.inventories.list.PlayerListMenu;
import me.redstom.beaconwarp.orm.repositories.Repositories;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PWarpCommand implements CommandExecutor {

    @Inject private Repositories repositories;

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {

        new PlayerListMenu(repositories).open((Player) sender);

        return false;
    }
}
