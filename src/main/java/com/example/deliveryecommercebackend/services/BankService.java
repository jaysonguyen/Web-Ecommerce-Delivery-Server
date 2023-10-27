package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.BankDTO;
import com.example.deliveryecommercebackend.exception.ResourceNotfoundException;
import com.example.deliveryecommercebackend.model.Bank;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    public List<Bank> getAllBanks() {
        try {
            return bankRepository.findAll();
        } catch(Exception ex) {
            System.out.printf("Get bank failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public ResponseEntity<Bank> getBankById(String code){
        Bank bank = bankRepository.findById(code)
                .orElseThrow(() -> new ResourceNotfoundException("Bank not exist with code:" + code));
        return ResponseEntity.ok(bank);
    }
    public ResponseEntity<Bank> updateBank(String code, BankDTO bankDetails) {
        Bank updateBank = bankRepository.findById(code)
                .orElseThrow(() -> new ResourceNotfoundException("Bank not exist with id: " + code));

        updateBank.setName(bankDetails.getName());
//        updateBank.setState(bankDetails.get());

        bankRepository.save(updateBank);

        return ResponseEntity.ok(updateBank);
    }
    public Bank createBank(Bank bankDetails) {
        return bankRepository.save(bankDetails);
    }

    public ResponseEntity<HttpStatus> deleteBank( String code){

        Bank bank = bankRepository.findById(code)
                .orElseThrow(() -> new ResourceNotfoundException("Bank not exist with code: " + code));

        bankRepository.delete(bank);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
