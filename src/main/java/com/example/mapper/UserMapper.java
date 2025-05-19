package com.example.mapper;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.dto.UserDTO;

import java.util.Objects;
import java.util.stream.Collectors;

public class UserMapper {

    private static UserMapper INSTANCE;

    public static synchronized UserMapper getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new UserMapper();
        }

        return INSTANCE;
    }

    public UserDTO userToUserDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getRoles().stream().map(Role::getName).toList().toArray(new String[0]));
    }
}
