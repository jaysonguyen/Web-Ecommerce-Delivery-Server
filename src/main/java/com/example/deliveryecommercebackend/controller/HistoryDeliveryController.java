package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.DTO.HistoryDeliveryDTO;
import com.example.deliveryecommercebackend.services.HistoryDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/history/delivery")
@RestController
public class HistoryDeliveryController {


   @Autowired
    private HistoryDeliveryService hisSer;


//   @PostMapping
//    public ResponseEntity<?> insertHistoryDelivery(HistoryDeliveryDTO hisDTO) {
//       try {
//           var insertDeliveryHistory = hisSer.confirmReceivePackage(hisDTO);
//           if(insertDeliveryHistory == true) {
//              return ResponseEntity.ok().body("Insert success");
//           }
//
//           return ResponseEntity.badRequest().body("Insert failed");
//       }
//        catch (Exception ex) {
//           return ResponseEntity.badRequest().body("Error from controller, " + ex.getMessage());
//        }
//   }

    @PostMapping
    public ResponseEntity<String> createDeliveryHistory(@RequestParam("order_id") String orderId,
                                                        @RequestParam("branch_id") String branchId,
                                                        @RequestParam("shipper_code") String shipperCode,
                                                        @RequestParam("money_collect") long moneyCollect,
                                                        @RequestParam("state") String state,
                                                        @RequestParam("reason_reject") String reason,
                                                        @RequestParam("image") String image
                                                        ) {
        try {


            var insertHistory = hisSer.confirmReceivePackage(orderId, branchId, moneyCollect, shipperCode, state, reason, image);
            if(insertHistory == true) {
                return ResponseEntity.ok().body("Insert success");

            }
            return new ResponseEntity<>("Delivery history created successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating delivery history: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
