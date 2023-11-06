package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.DTO.OrderCreateDTO;
import com.example.deliveryecommercebackend.DTO.OrderDetailsDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
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

    private String order_code; //new

    private String action_code;
    private String city_code;

    private double ship_cost;

    //json
    @Column(columnDefinition = "TEXT")
    private String receiver;

    private String product_type_code;
    private LocalDateTime created;
    private LocalDateTime updated;

    @Column(columnDefinition = "TEXT")
    private String address;
    // cost of package
    private double cost;
    private double voucher_discount; //new
    private boolean collect_money;

    // json
    @Column(columnDefinition = "TEXT")
    private String product;
    // json
    @Column(columnDefinition = "TEXT")
    private String package_order;

    // shipper cost
    private double total_cost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    //user code
    private String shipper_code;

    public void setDataCreate(OrderCreateDTO orderDTO, User user) {
        this.user = user;

        this.order_code = orderDTO.getOrder_code();
        this.action_code = orderDTO.getAction_code();
        this.product = orderDTO.getProduct();
        this.package_order = orderDTO.getPackage_order();
//        this.ship_cost = orderDTO.getShip_cost();
        this.receiver = orderDTO.getReceiver();
        this.product_type_code = orderDTO.getProduct_type_code();

        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }
}
