package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Action;
import com.example.deliveryecommercebackend.model.Area;
import com.example.deliveryecommercebackend.model.City;
import com.example.deliveryecommercebackend.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSearchDTO {
    private String id;
    private String code;

    private String action_name;
    private String city_name;
    private String area_name;
    private double total;
    //json
    private String receiver;
    private Date created;

    public OrderSearchDTO(Order order, Action action, City city, Area area) {
        this.id = order.getOrder_id();
        this.code = order.getOrder_code();
        this.action_name = action.getName();
        this.city_name = city.getName();
        this.area_name = area.getName();
        this.total = order.getTotal_cost();
        this.receiver = order.getReceiver();
        this.created = order.getCreated();
    }

}
