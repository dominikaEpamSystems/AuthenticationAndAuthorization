package com.epam.AuthenticationAndAuthorization.repository;

import com.epam.AuthenticationAndAuthorization.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameIgnoreCase(String username);
}
