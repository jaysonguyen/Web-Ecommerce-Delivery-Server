package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Action;
import com.example.deliveryecommercebackend.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDisplayListDTO {
    private String order_id;
    private String order_code;

    private String action_name;
    private String receiver;

    private String product;

    private double collect_money;
    private double total_cost;

    private Date created;
    private Date updated;
    // sender
    private String user_name;
    public OrderDisplayListDTO(Order order, String actionName) {
        this.order_id = order.getOrder_id();
        this.order_code = order.getOrder_code();
        this.created = order.getCreated();
        this.updated = order.getUpdated();
        this.collect_money = order.getCollect_money();
        this.action_name = actionName;
        this.user_name = order.getUser().getFullName();
        this.total_cost = order.getTotal_cost();

        this.receiver = order.getReceiver();
        this.product = order.getProduct();
    }
}
