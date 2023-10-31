package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, String> {
    @Query("SELECT u FROM Store u WHERE u.user.user_id = :userId")
    List<Store> findStoreByUser(String userId);
}
