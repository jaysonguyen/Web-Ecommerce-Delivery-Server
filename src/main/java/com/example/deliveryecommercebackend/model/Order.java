package com.example.deliveryecommercebackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="delivery_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String order_id;

    private int order_action;
    private int order_city;
    private double ship_cost;

    @Column(columnDefinition = "TEXT")
    private String receiver;

    private String product_type;
    private LocalDateTime created;
    private LocalDateTime updated;

    @Column(columnDefinition = "TEXT")
    private String address;
    private double cost;
    private boolean collect_money;

    @Column(columnDefinition = "TEXT")
    private String product;

    @Column(columnDefinition = "TEXT")
    private String package_order;

    private double total_cost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private String shipper;


}
