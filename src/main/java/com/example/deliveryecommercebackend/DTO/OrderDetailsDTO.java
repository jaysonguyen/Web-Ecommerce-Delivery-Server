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
    private String order_code;

    private String action_code;
    private double ship_cost;
    //json
    private String receiver;
    private String product_type_name;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean collect_money;
    //json
    private String product;
    private String address;
    private String package_order;
    private String user_name;
    private String shipper_name;

    public OrderDetailsDTO(Order order, String cityName, String productTypeName, String shipperName) {
        this.product_type_name = productTypeName;
        this.shipper_name = shipperName;

        this.order_code = order.getOrder_code();
        this.order_id = order.getOrder_id();
        this.user_name = order.getUser().getUser_id();
        this.action_code = order.getAction_code();
        this.ship_cost = order.getShip_cost();
        this.created = order.getCreated();
        this.updated = order.getUpdated();
        this.collect_money = order.isCollect_money();
        //json
        this.address = order.getAddress();
        this.product = order.getProduct();
        this.receiver = order.getReceiver();
        this.package_order = order.getPackage_order();
    }

    public OrderDetailsDTO(Order order, String productTypeName) {
        this.product_type_name = productTypeName;

        this.order_code = order.getOrder_code();
        this.order_id = order.getOrder_id();
        this.user_name = order.getUser().getUser_id();
        this.action_code = order.getAction_code();
        this.ship_cost = order.getShip_cost();
        this.created = order.getCreated();
        this.updated = order.getUpdated();
        this.collect_money = order.isCollect_money();
        //json
        this.address = order.getAddress();
        this.product = order.getProduct();
        this.receiver = order.getReceiver();
        this.package_order = order.getPackage_order();
        this.shipper_name = "";
    }
}
