package com.epam.AuthenticationAndAuthorization.repository;

import com.epam.AuthenticationAndAuthorization.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
