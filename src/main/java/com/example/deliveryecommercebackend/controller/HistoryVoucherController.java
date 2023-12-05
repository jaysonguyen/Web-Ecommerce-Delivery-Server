package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.services.HistoryVoucherService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/history_voucher")
@RestController
public class HistoryVoucherController {
    @Autowired
    private HistoryVoucherService hisServices;

    @GetMapping("/voucher")
    @RequestBody
    public ResponseEntity<?> getHistoryListByVoucher(@RequestParam String voucherId) {
        try {
            System.out.println(voucherId);
            return hisServices.getHistoryByVoucher(voucherId);
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }
    @GetMapping("/order")
    @RequestBody
    public ResponseEntity<?> getHistoryListByOrder(@RequestParam String orderId) {
        try {
            return hisServices.getHistoryByOrder(orderId);
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }
}
