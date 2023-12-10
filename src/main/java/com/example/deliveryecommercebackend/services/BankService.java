package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.BankDropdownDTO;
import com.example.deliveryecommercebackend.exception.ResourceNotfoundException;
import com.example.deliveryecommercebackend.model.Bank;
import com.example.deliveryecommercebackend.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    public ResponseEntity<?> getAllBanks() {
        try {
            var bankList = bankRepository.findAll();

            List<BankDropdownDTO> bankDTOS = new ArrayList<>();
            for (var item : bankList) {
                BankDropdownDTO temp = new BankDropdownDTO(item);
                bankDTOS.add(temp);
            }

            return ResponseEntity.ok().body(bankDTOS);
        } catch(Exception ex) {
            System.out.printf("Get bank failed - Error: " + ex);
            return ResponseEntity.badRequest().body("Error");
        }
    }

    public ResponseEntity<Bank> getBankById(String code){
        Bank bank = bankRepository.findById(code)
                .orElseThrow(() -> new ResourceNotfoundException("Bank not exist with code:" + code));
        return ResponseEntity.ok(bank);
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
