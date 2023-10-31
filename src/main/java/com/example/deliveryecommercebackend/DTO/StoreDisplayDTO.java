package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.User;

import java.time.LocalDateTime;

public class StoreDisplayDTO {
    private String storeId;
    private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String des;
    private String address;
    private int state;
    private boolean isDefault;
    private String phone;
}
