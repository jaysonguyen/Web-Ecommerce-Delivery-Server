package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.StoreDTO;
import com.example.deliveryecommercebackend.model.Store;
import com.example.deliveryecommercebackend.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getStoreList(StoreDTO storeDTO) {
        try {
            var storeList = storeService.getStoreList();
            if(storeList != null) {
                return ResponseEntity.ok(storeList);
            }
        } catch (Exception ex) {
            System.out.printf("Error from server");
        }
        return ResponseEntity.badRequest().body("Get list store failed");
    }

    @PostMapping
    public ResponseEntity<?> createStore(@RequestBody StoreDTO storeDto) {
        try {
            var checkInsertStore = storeService.createStore(storeDto);
            if(checkInsertStore) {
                ResponseEntity.ok("Insert success");
            }
            return ResponseEntity.badRequest().body("Insert store failed");
        } catch (Exception ex) {
            System.out.println("Error from server - Error " + ex);
            return ResponseEntity.badRequest().body("Insert store failed" + ex);
        }
    }
}
