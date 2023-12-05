package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Voucher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderCreateDTO {
    private String action_code;
    private String order_code;
    private String package_order;
    private String product;
    private String address;
    private String city_code;
    private String area_code;
    private String receiver;
    private String user_id;
    private ArrayList<VoucherDTO> voucher_used_list;

    // shipper cost
    private double total_cost;
    // cost of package
    private double cost;
    private double voucher_discount; //new
    private boolean collect_money;

    private LocalDateTime created;
    private LocalDateTime updated;
}
