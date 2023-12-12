package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface VoucherRepository extends JpaRepository<Voucher, String> {
    //crud method
    @Query("SELECT u FROM Voucher u WHERE u.is_deleted = false")
    List<Voucher> findNoneDeleteVoucher();
    @Query("SELECT u FROM Voucher u WHERE u.is_deleted = false AND u.voucher_id = :id")

    Voucher findNoneDeleteVoucherByID(@Param("id") String voucherId);

    @Query("SELECT u FROM Voucher u WHERE u.is_deleted = false AND u.status = :status ")
    List<Voucher> findNoneDeleteVoucherByStatus(@Param("status") String status);
    @Query("SELECT u FROM Voucher u WHERE u.is_deleted = false AND u.code = :code ")
    Voucher findNoneDeleteVoucherByCode(@Param("code") String code);
}
