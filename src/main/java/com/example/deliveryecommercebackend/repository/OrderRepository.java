package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Action;
import com.example.deliveryecommercebackend.model.City;
import com.example.deliveryecommercebackend.model.Order;
import com.example.deliveryecommercebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT u FROM Order u WHERE u.user = :user")
    List<Order> findOrderByUser(@Param("user") User user);

    @Query("SELECT u FROM Order u WHERE u.order_id = :id")
    Order findOrderById(@Param("id") String orderId);

    @Query("SELECT u FROM Order u WHERE u.action_code = :action_code")
    List<Order> findOrderByAction(@Param("action_code") String action_code);

    @Query("SELECT u FROM Order u WHERE u.order_code = :code")
    Order findOrderByCode(@Param("code") String orderCode);

    @Query("SELECT u FROM Order u WHERE u.shipper_code= :shipperCode")
    List<Order> findOrderByShipperAssigned(@Param("shipperCode") String code);
}
