package com.dians.navigation.repository;

import com.dians.navigation.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    Optional<AdminUser> findByUsernameAndPassword(String username, String password);
    Optional<AdminUser> findByUsername(String username);
}
