package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.DTO.StoreDTO;
import com.example.deliveryecommercebackend.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, String> {

    @Query("select  u from Store u where u.is_delete = false")
    List<Store> findStoreByUserId(String user_id);

}
