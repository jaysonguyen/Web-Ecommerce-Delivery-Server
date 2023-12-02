package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.DTO.VoucherDTO;
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
    private int points;
    @Column(name = "used")
    private int used;
    @Column(name = "is_deleted")
    private boolean is_deleted;
    @Column(name = "status")
    private String status;

    public Voucher(VoucherDTO vourcherDTO) {
        this.voucher_id = vourcherDTO.getVoucherId();
        this.name = vourcherDTO.getName();
        this.cost = vourcherDTO.getCost();
        this.created = Date.valueOf(LocalDate.now());
        this.period = vourcherDTO.getPeriod();
        this.quantity = vourcherDTO.getQuantity();
        this.points = vourcherDTO.getPoints();
        this.used = vourcherDTO.getUsed();
        this.is_deleted = false;
        this.status = "Valid";
    }
}
