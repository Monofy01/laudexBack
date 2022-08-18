package com.brian.laudexback.repository;

import com.brian.laudexback.model.Role;
import com.brian.laudexback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
