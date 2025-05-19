package com.example.service.impl;

import com.example.service.JwtService;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.jwt.Claims;

import java.time.Instant;
import java.util.Set;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    @Override
    public String generateToken(String username, Set<String> roles) {
        return Jwt.issuer("https://example.com/issuer")
                .upn(username)
                .groups(roles)
                .expiresIn(Instant.now().getEpochSecond() + 3600)
                .claim(Claims.birthdate.name(), "2001-07-13")
                .sign();
    }
}
