package com.sck.helpdesk.dto;

import com.sck.helpdesk.domain.UserEntity;

public class UserEditForm {

    private String username;
    private UserEntity.UserType type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserEntity.UserType getType() {
        return type;
    }

    public void setType(UserEntity.UserType type) {
        this.type = type;
    }
}
