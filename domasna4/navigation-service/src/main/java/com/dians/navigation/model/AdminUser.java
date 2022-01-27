package com.dians.navigation.model;

import lombok.Data;

@Data
public class AdminUser {
    private Long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    public AdminUser(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public AdminUser() {
    }
}
