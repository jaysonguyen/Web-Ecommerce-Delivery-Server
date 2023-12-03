package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.DTO.StoreDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

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
    private Date created;
    private Date updated;
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

        this.created = Date.valueOf(LocalDate.now());
        this.updated = Date.valueOf(LocalDate.now());
        this.address = (storeDto.getAddress());
        this.name = (storeDto.getName());
        this.des = (storeDto.getDes());
        this.phone = (storeDto.getPhone());
        this.state = (storeDto.getState());
        this.code = (storeDto.getStore_code());
        this.user = (user);
    }

    public Store(User user) {
        this.code = user.getCode() + new Random().nextInt(1000, 9999);
        this.name = "store default";
        this.created = Date.valueOf(LocalDate.now());
        this.updated = Date.valueOf(LocalDate.now());
        this.des = "This is store des";
        this.is_delete = false;
        this.address = "";
        this.state = 1;
        this.is_default = true;
        this.phone = "";
        this.user = user;
    }
}
