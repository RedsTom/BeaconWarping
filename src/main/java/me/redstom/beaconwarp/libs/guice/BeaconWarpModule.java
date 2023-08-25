package me.redstom.beaconwarp.libs.guice;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import lombok.RequiredArgsConstructor;
import me.redstom.beaconwarp.libs.guice.annotations.PostConstruct;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@RequiredArgsConstructor
public class BeaconWarpModule extends AbstractModule implements TypeListener {

    private final JavaPlugin plugin;

    @Override
    protected void configure() {
        super.bindListener(Matchers.any(), this);

        super.bind(JavaPlugin.class)
             .toInstance(plugin);
        super.bind(PluginManager.class)
             .toInstance(Bukkit.getServer()
                               .getPluginManager());
    }

    @Override
    public <I> void hear(TypeLiteral<I> type,
                         TypeEncounter<I> encounter) {
        encounter.register((InjectionListener<I>) i ->
                Arrays.stream(i.getClass()
                               .getMethods())
                      .filter(m -> m.isAnnotationPresent(PostConstruct.class))
                      .forEach(m -> invokeMethod(m, i)));
    }

    private void invokeMethod(Method method,
                              Object object) {
        try {
            method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
