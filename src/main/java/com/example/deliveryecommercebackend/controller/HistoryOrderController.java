package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.services.HistoryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/api/history_order")
@RestController
public class HistoryOrderController {
    @Autowired
    private HistoryOrderService hisServices;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getHistoryListByOrder(@Param("orderId") String orderId) {
        try {
            return hisServices.getHistoryByOrder(orderId);
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }

}
