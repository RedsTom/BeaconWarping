package me.redstom.beaconwarp.libs.guice;

import com.google.inject.AbstractModule;
import me.redstom.beaconwarp.BeaconWarp;
import me.redstom.beaconwarp.orm.entities.User;
import me.redstom.beaconwarp.orm.entities.Warp;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class BeaconHibernateModule
        extends AbstractModule {

    private final JavaPlugin plugin;

    public BeaconHibernateModule(BeaconWarp plugin) {
        this.plugin = plugin;
    }

    @Override protected void configure() {
        bind(SessionFactory.class).toInstance(this.createSessionFactory());
    }

    private SessionFactory createSessionFactory() {
        FileConfiguration config   = plugin.getConfig();
        String            url      = config.getString("database.url");
        String            port     = config.getString("database.port");
        String            name     = config.getString("database.name");
        String            user     = config.getString("database.user");
        String            password = config.getString("database.pass");
        boolean           debug    = config.contains("debug");

        Properties settings = new Properties();
        settings.put(AvailableSettings.DRIVER, "org.postgresql.Driver");
        settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        settings.put(AvailableSettings.URL, "jdbc:postgresql://%s:%s/%s".formatted(url, port, name));
        settings.put(AvailableSettings.USER, user);
        settings.put(AvailableSettings.PASS, password);
        if(debug) {
            settings.put(AvailableSettings.SHOW_SQL, true);
            settings.put(AvailableSettings.HBM2DDL_AUTO, "create-drop");
        } else {
            settings.put(AvailableSettings.HBM2DDL_AUTO, "update");
        }

        Configuration configuration =
                new Configuration().setProperties(settings).addAnnotatedClass(User.class).addAnnotatedClass(Warp.class);

        ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
