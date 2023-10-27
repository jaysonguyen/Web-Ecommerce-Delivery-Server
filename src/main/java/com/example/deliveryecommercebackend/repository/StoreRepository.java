package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.DTO.StoreDTO;
import com.example.deliveryecommercebackend.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, String> {

}
