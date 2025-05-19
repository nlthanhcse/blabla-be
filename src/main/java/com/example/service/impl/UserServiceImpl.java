package com.example.service.impl;

import com.example.constant.Constant;
import com.example.domain.User;
import com.example.domain.UserQueryBuilder;
import com.example.dto.UserDTO;
import com.example.exception.UserNotFoundException;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.text.MessageFormat;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final EntityManager em;

    public UserServiceImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public UserDTO getUserById(String id) {

        UserQueryBuilder queryBuilder = new UserQueryBuilder(em);

        User user = queryBuilder.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        MessageFormat.format(Constant.Message.USER_NOT_FOUND_WITH_ID, id)
                ));

        return UserMapper.getInstance().userToUserDTO(user);
    }

    @Override
    public UserDTO getUserUsername(String username) {

        UserQueryBuilder queryBuilder = new UserQueryBuilder(em);

        User user = queryBuilder.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        MessageFormat.format(Constant.Message.USER_NOT_FOUND_WITH_USERNAME, username)
                ));

        return UserMapper.getInstance().userToUserDTO(user);
    }
}
