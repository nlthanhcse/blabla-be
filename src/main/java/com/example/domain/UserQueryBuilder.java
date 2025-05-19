package com.example.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserQueryBuilder {

    private final EntityManager em;

    public UserQueryBuilder(EntityManager em) {
        this.em = em;
    }

    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = em.createNamedQuery(User.FindByUsernameNamedQuery.NAME, User.class);
        query.setParameter(User.FindByUsernameNamedQuery.PARAM_USERNAME, username);
        return query.getResultStream().findFirst();
    }

    public Optional<User> findById(String id) {
        TypedQuery<User> query = em.createNamedQuery(User.FindByIdNamedQuery.NAME, User.class);
        query.setParameter(User.FindByIdNamedQuery.PARAM_ID, id);
        return query.getResultStream().findFirst();
    }

    public Set<Role> findRolesByUsername(String id) {
        TypedQuery<Role> query = em.createNamedQuery(User.FindRolesByUsernameNamedQuery.NAME, Role.class);
        query.setParameter(User.FindRolesByUsernameNamedQuery.PARAM_USERNAME, id);
        return new HashSet<>(query.getResultList());
    }

    public Set<Role> findRolesByUserId(String id) {
        TypedQuery<Role> query = em.createNamedQuery(User.FindRolesByIdNamedQuery.NAME, Role.class);
        query.setParameter(User.FindRolesByIdNamedQuery.PARAM_ID, id);
        return new HashSet<>(query.getResultList());
    }
}
