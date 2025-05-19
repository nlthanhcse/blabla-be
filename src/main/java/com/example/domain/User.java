package com.example.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "_user")
@NamedQuery(
        name = User.FindByUsernameNamedQuery.NAME,
        query = User.FindByUsernameNamedQuery.QUERY
)
@NamedQuery(
        name = User.FindByIdNamedQuery.NAME,
        query = User.FindByIdNamedQuery.QUERY
)
@NamedQuery(
        name = User.FindRolesByUsernameNamedQuery.NAME,
        query = User.FindRolesByUsernameNamedQuery.QUERY
)
@NamedQuery(
        name = User.FindRolesByIdNamedQuery.NAME,
        query = User.FindRolesByIdNamedQuery.QUERY
)
public class User {

    @Id
    private String id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String id, String username, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
    }

    public static class FindByUsernameNamedQuery {

        private FindByUsernameNamedQuery() {}

        public static final String NAME = "User.findByUsername";
        public static final String QUERY = "SELECT u FROM User u WHERE u.username = :username";
        public static final String PARAM_USERNAME = "username";
    }

    public static class FindByIdNamedQuery {

        private FindByIdNamedQuery() {}

        public static final String NAME = "User.findById";
        public static final String QUERY = "SELECT u FROM User u WHERE u.id = :id";
        public static final String PARAM_ID = "id";
    }

    public static class FindRolesByUsernameNamedQuery {

        private FindRolesByUsernameNamedQuery() {}

        public static final String NAME = "User.findRolesByUsername";
        public static final String QUERY = "SELECT r FROM User u JOIN u.roles r WHERE u.username = :username";
        public static final String PARAM_USERNAME = "username";
    }

    public static class FindRolesByIdNamedQuery {

        private FindRolesByIdNamedQuery() {}

        public static final String NAME = "User.findRolesById";
        public static final String QUERY = "SELECT r FROM User u JOIN u.roles r WHERE u.id = :id";
        public static final String PARAM_ID = "id";
    }
}

