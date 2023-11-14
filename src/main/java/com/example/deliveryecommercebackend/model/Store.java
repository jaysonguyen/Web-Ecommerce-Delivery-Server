package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.DTO.StoreDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity

@Table(name="store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String store_id;
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String des;
    private boolean is_delete;
    private String address;
    private int state;
    private boolean is_default;
    private String phone;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    public void setCreateData(StoreDTO storeDto, User user){
        System.out.println(storeDto.getStore_code());

        this.created = (LocalDateTime.now());
        this.updated = (LocalDateTime.now());
        this.address = (storeDto.getAddress());
        this.name = (storeDto.getName());
        this.des = (storeDto.getDes());
        this.phone = (storeDto.getPhone());
        this.state = (storeDto.getState());
        this.code = (storeDto.getStore_code());
        this.user = (user);
    }

}
