package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.StoreDTO;
import com.example.deliveryecommercebackend.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("*")
@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

//    @GetMapping
//    @ResponseBody
//    public ResponseEntity<?> getStoreList(String userID) {
//        try {
//            var storeList = storeService.getStoreList(userID);
//            if(storeList != null) {
//                return ResponseEntity.ok(storeList);
//            }
//        } catch (Exception ex) {
//            System.out.printf("Error from server");
//        }
//        return ResponseEntity.badRequest().body("Get list store failed");
//    }

    @PostMapping
    public ResponseEntity<?> createStore(@RequestBody StoreDTO storeDto) {
        try {
            var checkInsertStore = storeService.createStore(storeDto);
            return checkInsertStore;
        } catch (Exception ex) {
            System.out.println("Error from server - Error " + ex);
            return ResponseEntity.status(500).body("Error from controller: " + ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateStore(@RequestBody StoreDTO storeDTO) {
        try {
            var checkInsertStore = storeService.updateStore(storeDTO);
            if (checkInsertStore == HttpStatus.OK) {
                return ResponseEntity.ok("Insert success");
            }
            return ResponseEntity.status(checkInsertStore).body("Update store failed");
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex);
            return ResponseEntity.badRequest().body("Error from controller" + ex);
        }
    }

}
