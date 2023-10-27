package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.DTO.BankDTO;
import com.example.deliveryecommercebackend.exception.ResourceNotfoundException;
import com.example.deliveryecommercebackend.model.Bank;
import com.example.deliveryecommercebackend.repository.BankRepository;
import com.example.deliveryecommercebackend.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping
    public List<Bank> getBankList(){
        return bankService.getAllBanks();
    }

    @GetMapping("{code}")
    public ResponseEntity<Bank> getBankById(@PathVariable String code){
        return bankService.getBankById(code);
    }

    @PutMapping("{code}")
    public ResponseEntity<Bank> updateBank(@PathVariable String code,@RequestBody BankDTO bankDetails) {
        return bankService.updateBank(code, bankDetails);
    }

    @PostMapping
    public Bank createBank(@RequestBody Bank bankDetails) {
        return bankService.createBank(bankDetails);
    }

    @DeleteMapping("{code}")
    public ResponseEntity<HttpStatus> deleteBank(@PathVariable String code){
        return bankService.deleteBank(code);
    }
}
