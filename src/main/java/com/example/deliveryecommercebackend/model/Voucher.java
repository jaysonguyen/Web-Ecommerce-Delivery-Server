package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.DTO.VoucherDTO;
import com.example.deliveryecommercebackend.utils.Prototype;
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
public class Voucher implements Prototype {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "voucher_id")
    private String voucher_id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;
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
        this.code = vourcherDTO.getCode();
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

    public Voucher(Voucher vourcherDTO) {
        this.code = vourcherDTO.getCode();
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

    @Override
    public Prototype Clone() {
        Voucher newVoucher = new Voucher();

        newVoucher.setCode(this.code + this.voucher_id);
        newVoucher.setName(this.name);
        newVoucher.setCost(this.cost);
        newVoucher.setCreated(this.created);
        newVoucher.setPeriod(this.period);
        newVoucher.setQuantity(this.quantity);
        newVoucher.setPoints(this.points);
        newVoucher.setUsed(this.used);
        newVoucher.set_deleted(this.is_deleted);
        newVoucher.setStatus(this.status);

        return newVoucher;
    }
}
