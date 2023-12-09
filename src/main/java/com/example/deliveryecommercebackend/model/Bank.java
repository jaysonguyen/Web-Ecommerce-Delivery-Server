package com.example.deliveryecommercebackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String bank_id;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private boolean state;
    private boolean is_delete = false;

//    public Bank(String code, String name, Integer state) {
//        this.code = code;
//        this.name = name;
//        this.state = state;
//    }

//    public Bank(Bank bank) {
//        this.code = bank.getCode();
//        this.name = bank.getName();
//        this.state = bank.getState();
//    }
}
