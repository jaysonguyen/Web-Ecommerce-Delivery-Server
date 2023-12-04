package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="history_order")
public class HistoryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String history_id;
    private String order_id;
    private String branch_id;
    private String shipper_name;
    private Date date_time;
    //user name
    private String input_by;
    private String action_code;
    private String note;

    public HistoryOrder(Order order, User user, String shipper_name, String action_code, String note) {
        this.order_id = order.getOrder_id();
        this.branch_id = user.getBranch().getBranch_id();
        this.shipper_name = shipper_name;
        this.date_time = new Date();
        this.input_by = user.getFullName();
        this.action_code = action_code;
        this.note = note;
    }
}
