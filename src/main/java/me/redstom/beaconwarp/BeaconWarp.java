package me.redstom.beaconwarp;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import me.redstom.beaconwarp.commands.CommandInitializer;
import me.redstom.beaconwarp.events.ListenerInitializer;
import me.redstom.beaconwarp.libs.guice.BeaconHibernateModule;
import me.redstom.beaconwarp.libs.guice.BeaconWarpModule;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.translation.GlobalTranslator;
import net.kyori.adventure.translation.TranslationRegistry;
import net.kyori.adventure.util.UTF8ResourceBundleControl;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class BeaconWarp
        extends JavaPlugin {

    @Inject private ListenerInitializer listenerInitializer;
    @Inject private CommandInitializer  commandInitializer;

    @Override public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
            saveDefaultConfig();

            getLogger().severe("The configuration file did not exist... Exiting!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        TranslationRegistry registry = TranslationRegistry.create(Key.key("beaconwarp:translation"));

        for (Locale locale : Arrays.asList(Locale.FRANCE, Locale.US)) {
            ResourceBundle bundle = ResourceBundle.getBundle("texts.Bundle", locale,
                    UTF8ResourceBundleControl.get());
            registry.registerAll(locale, bundle, true);
        }

        GlobalTranslator.translator().addSource(registry);

        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

        Injector injector = Guice.createInjector(new BeaconWarpModule(this), new BeaconHibernateModule(this));
        injector.injectMembers(this);

        listenerInitializer.init(injector);
        commandInitializer.init(injector);
    }
}
