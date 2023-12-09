package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.DTO.AreaCreatedDTO;
import com.example.deliveryecommercebackend.DTO.CustomerBankDTO;
import com.example.deliveryecommercebackend.model.CustomerBank;
import com.example.deliveryecommercebackend.services.CustomerBankService;
import com.example.deliveryecommercebackend.services.HistoryVoucherService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/customer_bank")
@RestController
public class CustomerBankController {
    @Autowired
    private CustomerBankService customerBankService;

    @GetMapping("")
    @RequestBody
    public ResponseEntity<?> getBankListByCustomer(@RequestParam String customerId) {
        try {
            return customerBankService.getBankByUserId(customerId);
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> insertBankToCustomer(@org.springframework.web.bind.annotation.RequestBody CustomerBankDTO customerBank) {
        try {
            System.out.println(customerBank.getBank_name());
            return customerBankService.addBankToCustomer(customerBank);
        } catch (Exception ex) {
            System.out.println("Error from controller");
            return ResponseEntity.status(400).body("Server error");
        }
    }

    @PutMapping
    public ResponseEntity<?> deleteBankToCustomer(@org.springframework.web.bind.annotation.RequestBody CustomerBankDTO customerBank) {
        try {
            return customerBankService.deleteBankAccount(customerBank);
        } catch (Exception ex) {
            System.out.println("Error from controller");
            return ResponseEntity.status(400).body("Server error");
        }
    }
}
