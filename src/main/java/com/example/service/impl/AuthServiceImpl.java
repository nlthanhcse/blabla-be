package com.example.service.impl;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.domain.UserQueryBuilder;
import com.example.dto.AuthDTO;
import com.example.exception.BadCredentialsException;
import com.example.request.LoginRequest;
import com.example.service.AuthService;
import com.example.service.JwtService;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.util.Set;
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

        UserQueryBuilder queryBuilder = new UserQueryBuilder(em);

        User user = queryBuilder.findByUsername(loginRequest.getUsername())
                .orElseThrow(BadCredentialsException::new);

        if (!BcryptUtil.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException();
        }

        Set<String> roles = queryBuilder.findRolesByUserId(user.getId()).stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return new AuthDTO(
                jwtService.generateToken(user.getUsername(), roles),
                "",
                "Bearer"
        );
    }
}
