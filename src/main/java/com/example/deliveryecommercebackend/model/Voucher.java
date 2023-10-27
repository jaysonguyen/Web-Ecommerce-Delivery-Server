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
@Table(name="voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "voucher_id")
    private String voucher_id;

    @Column(name = "name")
    private String name;

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

    @Column(name = "is_deleted")
    private boolean is_deleted;

    @Column(name = "status")
    private String status;
}
