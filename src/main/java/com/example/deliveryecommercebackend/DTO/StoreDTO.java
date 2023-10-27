package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {

    private String storeId;
    private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String des;
    private boolean isDelete;
    private String address;
    private int state;
    private boolean isDefault;
    private String phone;
    private User user;

}
