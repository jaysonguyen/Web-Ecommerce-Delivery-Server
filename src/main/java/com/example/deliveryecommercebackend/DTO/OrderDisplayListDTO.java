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
    private String order_code;

    private String action_name;
    private String receiver;

    private String product_type_name;
    private String product;

    private boolean collect_money;

    private LocalDateTime created;
    private LocalDateTime updated;
    // sender
    private String user_name;
    public OrderDisplayListDTO(Order order, String actionName, String producTypeName) {
        this.order_id = order.getOrder_id();
        this.order_code = order.getOrder_code();
        this.created = order.getCreated();
        this.updated = order.getUpdated();
        this.product_type_name = producTypeName;
        this.collect_money = order.isCollect_money();
        this.action_name = actionName;
        this.user_name = order.getUser().getFullName();

        this.receiver = order.getReceiver();
        this.product = order.getProduct();
    }
}
