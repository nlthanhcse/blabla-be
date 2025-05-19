package com.example.dto;

public class UserDTO {

    private String id;
    private String username;
    private String[] roles;

    public UserDTO() {
    }

    public UserDTO(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDTO(String id, String username, String[] roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getRoles() {
        return roles;
    }
}
