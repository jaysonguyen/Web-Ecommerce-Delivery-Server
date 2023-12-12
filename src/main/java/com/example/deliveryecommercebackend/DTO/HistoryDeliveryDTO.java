package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDeliveryDTO {
    private String delivery_history_id;
    private String order_id;
    private String branch_id;
    private LocalDateTime data_time;
    private String input_by;
    private String state;
    private String image;
    private String shipper_code;
    private long money_collect;
}
