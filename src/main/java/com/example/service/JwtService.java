package com.example.service;

import java.io.IOException;
import java.util.Set;

public interface JwtService {

    String generateToken(String username, Set<String> roles) throws Exception;
}
