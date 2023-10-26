package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findUserByAccount(String account);
    User findUserByAccountAndPassword(String account, String password);
}
