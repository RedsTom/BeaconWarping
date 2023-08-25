package me.redstom.beaconwarp.libs.guice;

import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public class BeaconWarpModule
        extends AbstractModule {

    private final JavaPlugin plugin;

    @Override protected void configure() {
        super.bind(JavaPlugin.class).toInstance(plugin);
        super.bind(PluginManager.class).toInstance(Bukkit.getServer().getPluginManager());
    }
}
