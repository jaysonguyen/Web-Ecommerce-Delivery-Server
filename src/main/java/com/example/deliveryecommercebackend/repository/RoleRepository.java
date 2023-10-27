package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    //crud method

}
