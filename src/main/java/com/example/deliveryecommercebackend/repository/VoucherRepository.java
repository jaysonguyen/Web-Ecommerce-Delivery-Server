package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface VoucherRepository extends JpaRepository<Voucher, String> {
    //crud method
    @Query("SELECT u FROM Voucher u WHERE u.is_deleted = false")
    List<Voucher> findNoneDeleteVoucher();

}
