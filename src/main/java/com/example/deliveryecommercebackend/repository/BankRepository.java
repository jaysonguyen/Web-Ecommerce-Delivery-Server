package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, String> {
    @Query("SELECT u FROM Bank u WHERE u.name = :name")
    Bank findBankByName(@Param("name") String bankName);
    //crud method

}
