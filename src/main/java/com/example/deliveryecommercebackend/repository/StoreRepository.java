package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.user.Store;
import com.example.deliveryecommercebackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, String> {
    @Query("SELECT u FROM Store u WHERE u.user = :user")
    List<Store> findStoreByUser(@Param("user") User user);
    @Query("SELECT u FROM Store u WHERE u.code = :code")
    Store findByCode(@Param("code") String storeCode);

    @Query("SELECT u FROM Store u WHERE u.store_id = :id")
    Store findStoreById(@Param("id") String storeID);
}
