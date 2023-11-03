package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Action;
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
public class OrderDisplayListDTO {
    private String order_id;

    private Action action;
    private String receiver_name;
    private String address;

    private String product_type_name;
    private String product;

    private double cost;
    private double total_cost;
    private boolean collect_money;

    private LocalDateTime created;
    private LocalDateTime updated;
    // sender
    private String user_name;

    public OrderDisplayListDTO(Order order, Action action) {
        this.order_id = order.getOrder_id();
        this.created = order.getCreated();
        this.updated = order.getUpdated();
        this.address = order.getAddress();
        this.collect_money = order.isCollect_money();
        //json
        this.product = order.getProduct();
        this.action = action;
        this.user_name = order.getUser().getFullName();
    }
}
