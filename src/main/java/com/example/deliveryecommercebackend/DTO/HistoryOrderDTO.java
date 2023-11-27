package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Order;
import com.example.deliveryecommercebackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryOrderDTO {
    private String order_id;
    private String branch_id;
    private String shipper_name;
    private Date data_time;
    //user name
    private String input_by;
    private String action_code;

    public HistoryOrderDTO(Order order, User user, String shipper_name, String action_code) {
        this.order_id = order.getOrder_id();
        this.branch_id = user.getBranch().getBranch_id();
        this.shipper_name = shipper_name;
        this.data_time = new Date();
        this.input_by = user.getFullName();
        this.action_code = action_code;
    }
}
