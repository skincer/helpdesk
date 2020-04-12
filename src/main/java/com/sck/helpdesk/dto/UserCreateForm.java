package com.sck.helpdesk.dto;

import com.sck.helpdesk.domain.UserEntity;

public class UserCreateForm {

    private String username;
    private String password;
    private UserEntity.UserType type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity.UserType getType() {
        return type;
    }

    public void setType(UserEntity.UserType type) {
        this.type = type;
    }
}
