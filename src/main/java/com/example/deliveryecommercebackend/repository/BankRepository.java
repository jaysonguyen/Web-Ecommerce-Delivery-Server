package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
    //crud method

}
