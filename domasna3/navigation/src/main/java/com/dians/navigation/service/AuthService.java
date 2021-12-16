package com.dians.navigation.service;

import com.dians.navigation.model.AdminUser;

public interface AuthService {

    AdminUser login(String username, String password);

    AdminUser register(String username, String password, String repeatPassword,
                       String name, String surname);
}
