package com.example.service;

import com.example.dto.UserDTO;

public interface UserService {

    UserDTO getUserById(String id);

    UserDTO getUserUsername(String username);
}
