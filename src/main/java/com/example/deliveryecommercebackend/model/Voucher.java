package com.example.deliveryecommercebackend.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name="voucher")
public class Voucher {
    @Id
    @Column(name = "voucher_id")
    private int voucher_id;

    @Column(name = "name_voucher")
    private String name_voucher;

    //percent
    @Column(name = "cost")
    private int cost;

    @Column(name = "created")
    private Date created;

    @Column(name = "period")
    private int period;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "used")
    private int used;

    @Column(name = "status")
    private String status;
}
