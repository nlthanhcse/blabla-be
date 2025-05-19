package com.example.service.impl;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.dto.AuthDTO;
import com.example.exception.BadCredentialsException;
import com.example.request.LoginRequest;
import com.example.service.AuthService;
import com.example.service.JwtService;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final EntityManager em;

    public AuthServiceImpl(JwtService jwtService, EntityManager em) {
        this.jwtService = jwtService;
        this.em = em;
    }

    @Override
    public AuthDTO login(LoginRequest loginRequest) throws Exception {

        TypedQuery<User> query = em.createQuery(
                "SELECT u.id, u.username, u.password FROM User u WHERE u.username = :username",
                User.class
        );
        query.setParameter("username", loginRequest.getUsername());
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            throw new BadCredentialsException();
        }

        User user = users.get(0);

        if (!BcryptUtil.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException();
        }

        Query rolesQuery = em.createNativeQuery("SELECT ur.role_name FROM _user_role ur WHERE ur.user_id = ?", String.class);
        rolesQuery.setParameter(1, user.getId());
        List<String> roles = rolesQuery.getResultList();

        return new AuthDTO(
                jwtService.generateToken(user.getUsername(), roles.stream().collect(Collectors.toSet())),
                "",
                "Bearer"
        );
    }
}
