package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VoucherRepository extends JpaRepository<Voucher, String> {
    //crud method

}
