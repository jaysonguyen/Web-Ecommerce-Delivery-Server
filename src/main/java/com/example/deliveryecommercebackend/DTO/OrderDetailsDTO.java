package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    private String order_id;

    private int action_code;
    private String city_name;
    private double ship_cost;
    //user's name
    private String receiver_name;
    private String product_type_name;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String address;
    private double cost;
    private boolean collect_money;
    private String product;
    private String package_order;
    private double total_cost;
    private String user_name;
    private String shipper_name;

    public OrderDetailsDTO(Order order, String cityName, String receiver_name, String productName, String shipperName) {
        this.city_name = cityName;
        this.receiver_name = receiver_name;
        this.product_type_name = productName;
        this.shipper_name = shipperName;

        this.order_id = order.getOrder_id();
        this.user_name = order.getUser().getUser_id();
        this.action_code = order.getAction_code();
        this.ship_cost = order.getShip_cost();
        this.created = order.getCreated();
        this.updated = order.getUpdated();
        this.address = order.getAddress();
        this.cost = order.getCost();
        this.collect_money = order.isCollect_money();
        this.product = order.getProduct();
        this.package_order = order.getPackage_order();
        this.total_cost = order.getTotal_cost();
    }
}
