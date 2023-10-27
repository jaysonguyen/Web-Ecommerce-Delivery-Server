package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User findUserByAccount(String account);
    User findUserByAccountAndPassword(String account, String password);
    User findUsersByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.is_delete = false")
    List<User> findNoneDeleteUser();
}
