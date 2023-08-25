package me.redstom.beaconwarp;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import me.redstom.beaconwarp.commands.CommandInitializer;
import me.redstom.beaconwarp.events.ListenerInitializer;
import me.redstom.beaconwarp.libs.guice.BeaconHibernateModule;
import me.redstom.beaconwarp.libs.guice.BeaconWarpModule;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BeaconWarp extends JavaPlugin {

    @Inject private ListenerInitializer listenerInitializer;
    @Inject private CommandInitializer commandInitializer;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
            saveDefaultConfig();

            getLogger().severe("The configuration file did not exist... Exiting!");
            Bukkit.getPluginManager()
                  .disablePlugin(this);
            return;
        }

        Thread.currentThread()
              .setContextClassLoader(getClass().getClassLoader());

        Injector injector = Guice.createInjector(
                new BeaconWarpModule(this),
                new BeaconHibernateModule(this));
        injector.injectMembers(this);

        listenerInitializer.init(injector);
        commandInitializer.init(injector);
    }
}
