package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Voucher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {

    private String voucherId;
    private String code;
    private String name;
    private boolean isDeleted;
    //percent
    private int cost;
    private Date created;
    private int period;
    private int quantity;
    private int points;
    private int used;
    private String status;


    public VoucherDTO(Voucher voucher) {
        this.voucherId = voucher.getVoucher_id();
        this.code = voucher.getCode();
        this.name = voucher.getName();
        this.isDeleted = voucher.is_deleted();
        this.cost = voucher.getCost();
        this.created = voucher.getCreated();
        this.period = voucher.getPeriod();
        this.quantity = voucher.getQuantity();
        this.used = voucher.getUsed();
        this.status = voucher.getStatus();
    }

    public void setData(Voucher voucher){
        voucherId = voucher.getVoucher_id();
        code = voucher.getCode();
        name = voucher.getName();
        isDeleted = voucher.is_deleted();
        cost = voucher.getCost();
        created = voucher.getCreated();
        period = voucher.getPeriod();
        quantity = voucher.getQuantity();
       used = voucher.getUsed();
       status = voucher.getStatus();
        points = voucher.getPoints();

    }
}

