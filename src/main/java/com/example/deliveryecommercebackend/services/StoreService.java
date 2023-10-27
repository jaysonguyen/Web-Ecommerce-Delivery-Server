package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.StoreDTO;
import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.example.deliveryecommercebackend.model.Store;
import com.example.deliveryecommercebackend.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepo;

    public StoreService(StoreRepository storeRepo) {
        this.storeRepo = storeRepo;
    }

    public List<Store> getStoreList() {
        try {
            var storeList = storeRepo.findAll();
            return storeList;
        } catch (Exception ex) {
            System.out.printf("Error from services");
        }

        return Collections.emptyList();
    }

    public boolean createStore(StoreDTO storeDto) {
        try {
            var store = new Store();
            store.setCreated(LocalDateTime.now());
            store.setUpdated(LocalDateTime.now());
            store.setAddress(storeDto.getAddress());
            store.setName(storeDto.getName());
            store.setDes(storeDto.getDes());
            store.setPhone(storeDto.getPhone());
            store.setState(store.getState());
            store.setUser(store.getUser());

            Store storeInsert = storeRepo.save(store);
            if(storeInsert != null) {
                return true;
            }

        } catch (Exception ex) {
            System.out.printf("Error from services - Error: "  + ex);
        }
        return false;

    }

}
