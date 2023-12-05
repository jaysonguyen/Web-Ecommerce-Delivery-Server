package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.DTO.CustomerBankDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="customer_bank")
public class CustomerBank {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    private String user_id;
    private String bank_number;
    private String bank_name;
    private Date date_time;

    public CustomerBank(CustomerBankDTO customerBank) {
        this.user_id = customerBank.getUser_id();
        this.bank_name = customerBank.getBank_name();
        this.bank_number = customerBank.getBank_number();
        this.date_time = Date.valueOf(LocalDate.now());
    }
}
