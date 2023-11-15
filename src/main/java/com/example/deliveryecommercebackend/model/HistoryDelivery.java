package com.example.deliveryecommercebackend.model;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDelivery {

    private String order_code;
    private String branch_code;
    private String shipper_name;
    private LocalDateTime date_time;
    private String input_by;
    private String state;
}
