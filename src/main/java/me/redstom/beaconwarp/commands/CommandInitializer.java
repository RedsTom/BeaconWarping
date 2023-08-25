package me.redstom.beaconwarp.commands;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.redstom.beaconwarp.common.Initializer;
import org.bukkit.plugin.java.JavaPlugin;

@Singleton
public class CommandInitializer
        implements Initializer {

    @Inject private JavaPlugin plugin;

    @Override public void init(Injector injector) {
        plugin.getCommand("pwarp").setExecutor(injector.getInstance(PWarpCommand.class));
    }
}
