package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.Role;
import com.example.deliveryecommercebackend.model.user.Shipper;
import com.example.deliveryecommercebackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.account = :account")
    User findUserByAccount(@Param("account") String account);
    @Query("SELECT u FROM User u WHERE u.user_id = :id")
    User findUserById(@Param("id") String id);
    @Query("SELECT u FROM User u WHERE u.user_id = :id")
    Shipper findShipperById(@Param("id") String id);
    User findUserByAccountAndPassword(String account, String password);
    User findUsersByEmail(String email);

    @Query("SELECT U FROM User U WHERE U.code =:user_code")
    User findUsersByCode(@Param("user_code") String code);
    @Query("SELECT u FROM User u WHERE u.role = :role AND u.is_delete = false")
    List<User> findUsersByRole(@Param("role") Role role);
    @Query("SELECT u FROM User u WHERE u.role = :role ANd u.code = :code AND u.is_delete = false")
    List<User> findNoneDeleteShipperByCode(@Param("code") String userCode, @Param("role") Role role);

    @Query("SELECT u FROM User u WHERE u.is_delete = false")
    List<User> findNoneDeleteUser();

    @Query("SELECT u FROM User u WHERE u.branch = :branch AND u.is_delete = false AND u.role = :role")
    List<User> findShipperByBranch(@Param("branch") Branch branch, @Param("role") Role role);

    @Query("SELECT u FROM User u WHERE u.is_delete = false AND u.user_id = :id")
    User getUserById(@Param("id") String userId);

    @Query("SELECT u FROM User u WHERE u.is_delete = false AND u.code = :code")
    User findUserByCode(@Param("code") String code);
}
