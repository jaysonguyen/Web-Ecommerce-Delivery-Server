package com.example.deliveryecommercebackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderCreateDTO {
    private String action_code;
    private boolean collect_money;
    private String order_code;
    private String package_order;
    private String product;
    private String address;
    private String city_code;
    private String area_code;
    private String receiver;
    private String user_id;

    private LocalDateTime created;
    private LocalDateTime updated;
}
