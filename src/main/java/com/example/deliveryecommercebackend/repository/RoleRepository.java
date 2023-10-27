package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByRoleId(int roleId);
}
