package com.example.service;

import com.example.dto.AuthDTO;
import com.example.request.LoginRequest;

import java.io.IOException;

public interface AuthService {

    AuthDTO login(LoginRequest loginRequest) throws Exception;
}
