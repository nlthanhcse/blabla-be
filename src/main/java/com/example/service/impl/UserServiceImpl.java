package com.example.service.impl;

import com.example.constant.Constant;
import com.example.domain.User;
import com.example.dto.UserDTO;
import com.example.exception.UserNotFoundException;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;

import java.text.MessageFormat;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserById(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        MessageFormat.format(Constant.Message.USER_NOT_FOUND_WITH_ID, id)
                ));

        return UserMapper.getInstance().userToUserDTO(user);
    }

    @Override
    public UserDTO getUserUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        MessageFormat.format(Constant.Message.USER_NOT_FOUND_WITH_USERNAME, username)
                ));

        return UserMapper.getInstance().userToUserDTO(user);
    }
}
