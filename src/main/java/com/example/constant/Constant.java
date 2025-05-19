package com.example.constant;

public class Constant {

    public enum ROLE {
        ADMIN,
        USER;
    }

    public static class UserAttributeName {

        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String ROLES = "roles";
    }

    public static class Message {
        public static final String USER_NOT_FOUND_WITH_USERNAME = "User with username {0} not found";
        public static final String USER_NOT_FOUND_WITH_ID = "User with id {0} not found";
    }
}
