package com.example.deliveryecommercebackend.repository;


import com.example.deliveryecommercebackend.model.CustomerBank;
import com.example.deliveryecommercebackend.model.HistoryVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerBankRepository extends JpaRepository<CustomerBank, String> {
    @Query("SELECT u FROM CustomerBank u WHERE u.user_id = :userId ORDER BY u.date_time ASC ")
    List<CustomerBank> findBankListByUserId(@Param("userId") String userId);
}
