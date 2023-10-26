package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, String> {
    //crud method

}
