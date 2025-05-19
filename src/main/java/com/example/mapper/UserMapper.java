package com.example.mapper;

import com.example.domain.User;
import com.example.dto.UserDTO;

import java.util.Objects;

public class UserMapper {

    private static UserMapper INSTANCE;

    public static synchronized UserMapper getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new UserMapper();
        }

        return INSTANCE;
    }

    public UserDTO userToUserDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername());
    }
}
