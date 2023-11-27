package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.DTO.HistoryDeliveryDTO;
import com.example.deliveryecommercebackend.services.HistoryDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/history/delivery")
@RestController
public class HistoryDeliveryController {


   @Autowired
    private HistoryDeliveryService hisSer;


   @PostMapping
    public ResponseEntity<?> insertHistoryDelivery(HistoryDeliveryDTO hisDTO) {
       try {
           var insertDeliveryHistory = hisSer.confirmReceivePackage(hisDTO);
           if(insertDeliveryHistory == true) {
              return ResponseEntity.ok().body("Insert success");
           }

           return ResponseEntity.badRequest().body("Insert failed");
       }
        catch (Exception ex) {
           return ResponseEntity.badRequest().body("Error from controller, " + ex.getMessage());
        }
   }


}
