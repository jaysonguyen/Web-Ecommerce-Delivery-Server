package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Store;
import com.example.deliveryecommercebackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreDisplayDTO {
    private String store_id;
    private String store_code;
    private String name;
    private Date created;
    private int state;

    public StoreDisplayDTO(Store store) {
        this.store_id = store.getStore_id();
        this.store_code = store.getCode();
        this.name = store.getName();
        this.created = store.getCreated();
        this.state = store.getState();
    }
}
