package com.example.config;

import com.example.domain.Role;
import com.example.domain.User;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

@ApplicationScoped
public class Startup {

    private final Logger log = LoggerFactory.getLogger(Startup.class);

    @Inject
    EntityManager entityManager;

    void onStartup(@Observes StartupEvent event) {
        log.info("Application is starting...");
        initializeData();
        log.info("Application started with initial data");
    }

    @Transactional
    void initializeData() {
        if (entityManager.createQuery("SELECT COUNT(r) FROM Role r", Long.class).getSingleResult() == 0) {
            Role adminRole = new Role("ADMIN");
            entityManager.persist(adminRole);

            Role userRole = new Role("USER");
            entityManager.persist(userRole);

            User user1 = new User("user1", BcryptUtil.bcryptHash("user1password"), Set.of(adminRole));
            entityManager.persist(user1);

            User user2 = new User("user2", BcryptUtil.bcryptHash("user2password"), Set.of(userRole));
            entityManager.persist(user2);

            entityManager.flush();
            log.info("Initial data created");
        } else {
            log.info("Data already exists, skipping initialization");
        }
    }
}
