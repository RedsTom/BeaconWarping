package me.redstom.beaconwarp.orm.repositories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import me.redstom.beaconwarp.common.RandomNameGenerator;
import me.redstom.beaconwarp.orm.entities.User;
import me.redstom.beaconwarp.orm.entities.Warp;
import org.bukkit.Location;
import org.bukkit.Material;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

@Singleton
public class WarpRepository {

    @Inject SessionFactory      sessionFactory;
    @Inject RandomNameGenerator randomNameGenerator;

    @Transactional public Warp create(User user, Location location) {
        Warp warp = Warp.builder()
                .name(randomNameGenerator.pickOne())
                .world(location.getWorld().getName())
                .x(location.getBlockX())
                .y(location.getBlockY())
                .z(location.getBlockZ())
                .state(Warp.State.ENABLED)
                .side(Warp.Side.TOP)
                .icon(Material.COMPASS)
                .user(user)
                .build();

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(warp);
            tx.commit();
        }

        return warp;
    }

    public Optional<Warp> findWarpOn(Location location) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Warp> cr   = cb.createQuery(Warp.class);
            Root<Warp>          root = cr.from(Warp.class);

            CriteriaQuery<Warp> query = cr.select(root)
                    .where(cb.and(cb.equal(root.get("x"), location.getBlockX()),
                            cb.equal(root.get("y"), location.getBlockY()),
                            cb.equal(root.get("z"), location.getBlockZ())));

            return Optional.ofNullable(session.createQuery(query).getSingleResultOrNull());
        }
    }

    @Transactional public void update(Warp warp) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(warp);
            tx.commit();
        }
    }

    @Transactional public void delete(Warp warp) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(warp);
            tx.commit();
        }
    }
}
