package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.StoreDTO;
import com.example.deliveryecommercebackend.model.Store;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.repository.StoreRepository;
import com.example.deliveryecommercebackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class StoreService {

    @Autowired
    private StoreRepository storeRepo;
    @Autowired
    private UserRepository userRepo;

    public StoreService(StoreRepository storeRepo, UserRepository userRepo) {
        this.storeRepo = storeRepo;
        this.userRepo = userRepo;
    }

    public ResponseEntity<?> getStoreList(String userId) {
        try {
            User user = userRepo.findUserById(userId);
            if(user.getUser_id() == null) {
                return ResponseEntity.badRequest().body("User not found");
            }

            var storeList = storeRepo.findStoreByUser(user);
            return ResponseEntity.ok().body(storeList);
        } catch (Exception ex) {
            System.out.printf("Error from services");
            return ResponseEntity.status(500).body(Collections.emptyList());
        }

    }

    public ResponseEntity<?> createStore(StoreDTO storeDto) {
        try {
            //check exists
            Store checkStore = storeRepo.findByCode(storeDto.getStore_code());
            if(checkStore != null) {
                return ResponseEntity.badRequest().body("Store code is exists");
            }


            var user = userRepo.findUserById(storeDto.getUser_id());
            var store = new Store();
            store.setCreateData(storeDto, user);

            Store storeInsert = storeRepo.save(store);
            if(storeInsert != null) {
                return ResponseEntity.ok().body("Create store successfully");
            } else {
                return ResponseEntity.badRequest().body("Create store failed");
            }

        } catch (Exception ex) {
            System.out.printf("Error from services - Error: "  + ex);
            return ResponseEntity.status(400).body("Error from server: " + ex.getMessage());
        }

    }

    public HttpStatus updateStore(StoreDTO storeDTO) {
        var store = storeRepo.findById(storeDTO.getStore_id()).get();
        if(store == null) {
            return HttpStatus.NOT_FOUND;
        }
        store.setName(storeDTO.getName());
        store.setState(storeDTO.getState());
        store.setDes(storeDTO.getDes());
        store.set_default(storeDTO.isDefault());
        try {
            var checkSaveStore = storeRepo.save(store);
            if(checkSaveStore == null)
                return HttpStatus.BAD_REQUEST;
            return HttpStatus.OK;
        } catch (Exception ex) {
            System.out.printf("Error from service" + ex);
            return HttpStatus.BAD_REQUEST;
        }
    }

    public ResponseEntity<?> getStoreByID(String storeID) {
        try {
            System.out.println(storeID);
            Store store = storeRepo.findStoreById(storeID);
            if(store == null) {
                return ResponseEntity.badRequest().body("Store not found");
            }
            return ResponseEntity.ok().body(store);
        } catch (Exception ex) {
            System.out.printf("Error from services - Error: " + ex.getMessage());
            return ResponseEntity.status(500).body(Collections.emptyList());
        }
    }
}
