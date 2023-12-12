package com.example.deliveryecommercebackend.repository;


import com.example.deliveryecommercebackend.model.HistoryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryOrderRepository extends JpaRepository<HistoryOrder, String> {
    @Query("SELECT u FROM HistoryOrder u WHERE u.order_id = :orderId ORDER BY u.date_time ASC ")
    List<HistoryOrder> findHistoryByOrder(@Param("orderId") String orderId);
}
