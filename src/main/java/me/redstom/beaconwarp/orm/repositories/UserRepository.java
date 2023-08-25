package me.redstom.beaconwarp.orm.repositories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import me.redstom.beaconwarp.orm.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class UserRepository {
    @Inject SessionFactory sessionFactory;

    @Transactional
    public User create(UUID id) {
        User user = User.builder()
                        .uniqueId(id)
                        .build();

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        }

        return user;
    }

    public Optional<User> get(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(User.class, id));
        }
    }

    public User getOrCreate(UUID id) {
        return get(id).orElseGet(() -> this.create(id));
    }

    @Transactional
    public void update(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        }
    }

    public List<User> getAllWithWarp() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);

            CriteriaQuery<User> query = cr.select(root)
                                          .where(cb.isNotEmpty(root.get("warps")));

            return session.createQuery(query)
                          .getResultList();
        }
    }
}
