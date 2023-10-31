package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT U FROM Role U WHERE U.name = :name")
    Role findRoleByName(String name);

}
