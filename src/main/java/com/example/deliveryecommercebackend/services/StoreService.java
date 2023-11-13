package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.StoreDTO;
import com.example.deliveryecommercebackend.model.Store;
import com.example.deliveryecommercebackend.repository.StoreRepository;
import com.example.deliveryecommercebackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class StoreService {

    @Autowired
    private StoreRepository storeRepo;
    private UserRepository userRepo;

    public StoreService(StoreRepository storeRepo, UserRepository userRepo) {
        this.storeRepo = storeRepo;
        this.userRepo = userRepo;
    }

//    public List<Store> getStoreList(String userId) {
//        try {
//            var storeList = storeRepo.findStoreByUser(userId);
//            return storeList;
//        } catch (Exception ex) {
//            System.out.printf("Error from services");
//        }
//
//        return Collections.emptyList();
//    }

    public boolean createStore(StoreDTO storeDto) {
        try {
            var user = userRepo.findUserById(storeDto.getUser());
            var store = new Store();
            store.setCreated(LocalDateTime.now());
            store.setUpdated(LocalDateTime.now());
            store.setAddress(storeDto.getAddress());
            store.setName(storeDto.getName());
            store.setDes(storeDto.getDes());
            store.setPhone(storeDto.getPhone());
            store.setState(storeDto.getState());
            store.setUser(user);

            Store storeInsert = storeRepo.save(store);
            if(storeInsert != null) {
                return true;
            }

        } catch (Exception ex) {
            System.out.printf("Error from services - Error: "  + ex);
        }
        return false;

    }



    public HttpStatus updateStore(StoreDTO storeDTO) {
        var store = storeRepo.findById(storeDTO.getStoreId()).get();
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

}
