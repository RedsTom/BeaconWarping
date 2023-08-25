package me.redstom.beaconwarp.events;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.redstom.beaconwarp.common.Initializer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Singleton
public class ListenerInitializer implements Initializer {

    @Inject
    private JavaPlugin plugin;

    @Inject
    private PluginManager pm;


    @Override
    public void init(Injector injector) {
        pm.registerEvents(injector.getInstance(BlockPlaceListener.class), plugin);
        pm.registerEvents(injector.getInstance(PlayerInteractListener.class), plugin);
        pm.registerEvents(injector.getInstance(BlockBreakListener.class), plugin);
        pm.registerEvents(injector.getInstance(BeaconListener.class), plugin);
    }
}
