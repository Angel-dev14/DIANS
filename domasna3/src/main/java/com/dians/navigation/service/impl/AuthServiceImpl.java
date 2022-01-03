package com.dians.navigation.service.impl;

import com.dians.navigation.model.AdminUser;
import com.dians.navigation.model.exceptions.InvalidArgumentsException;
import com.dians.navigation.model.exceptions.InvalidUserCredentialException;
import com.dians.navigation.model.exceptions.PasswordsDoNotMatchException;
import com.dians.navigation.model.exceptions.UsernameAlreadyExistsException;
import com.dians.navigation.repository.AdminUserRepository;
import com.dians.navigation.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AdminUserRepository adminUserRepository;

    public AuthServiceImpl(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public AdminUser login(String username, String password) {

        if(username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        return this.adminUserRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialException::new);
    }

    @Override
    public AdminUser register(String username, String password, String repeatPassword, String name, String surname) {

        if(username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        if(!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if(this.adminUserRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }

        AdminUser admin = new AdminUser(name, surname, username, password);
        return this.adminUserRepository.save(admin);
    }
}
