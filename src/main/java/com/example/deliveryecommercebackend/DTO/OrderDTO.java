package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String order_id;

    private int order_action;
    private int order_city;
    private double ship_cost;
    private String receiver;
    private String product_type;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String address;
    private double cost;
    private boolean collect_money;
    private String product;
    private String package_order;
    private double total_cost;
    private String user;
    private String shipper;
}
