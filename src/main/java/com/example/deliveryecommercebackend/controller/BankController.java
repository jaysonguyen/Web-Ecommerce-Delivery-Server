package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.model.Bank;
import com.example.deliveryecommercebackend.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    private BankRepository bankRepository;

    @GetMapping
    public List<Bank> getBankList(){
        return bankRepository.findAll();
    }
}
