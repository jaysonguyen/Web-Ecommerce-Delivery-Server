package com.example.deliveryecommercebackend.DTO;

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

    private String store_id;
    private String store_code;
    private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String des;
    private boolean isDelete;
    private String address;
    private int state;
    private boolean isDefault;
    private String phone;
    private String user_id;

}
