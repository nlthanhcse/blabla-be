package com.example.repository;

import com.example.domain.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepository {

    private final EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<User> findById(String id) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        List<User> users = query.getResultList();
        return users.isEmpty() ? Optional.empty() : Optional.of(users.getFirst());
    }

    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();
        return users.isEmpty() ? Optional.empty() : Optional.of(users.getFirst());
    }

}
