package com.example.deliveryecommercebackend.repository;


import com.example.deliveryecommercebackend.model.HistoryVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryVoucherRepository extends JpaRepository<HistoryVoucher, String> {
    @Query("SELECT u FROM HistoryVoucher u WHERE u.voucher_id = :voucherId ORDER BY u.date_time ASC ")
    List<HistoryVoucher> findHistoryByVoucher(@Param("voucherId") String voucherId);
    @Query("SELECT u FROM HistoryVoucher u WHERE u.order_id = :orderId ORDER BY u.date_time ASC ")
    List<HistoryVoucher> findHistoryByOrder(@Param("orderId") String orderId);

}
