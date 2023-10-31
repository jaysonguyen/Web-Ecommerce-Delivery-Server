package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.User;
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
public class VoucherDisplayDTO {

    private String voucherId;
    private String name;
    //percent
    private int cost;
    private Date created;
    private int period;
    private int quantity;
    private int used;
    private String status;

    public void setData(Voucher user){
        voucherId = user.getVoucher_id();
        name = (user.getName());
        cost = (user.getCost());
        created = (user.getCreated());
        period = (user.getPeriod());
        quantity = (user.getQuantity());
        used = (user.getUsed());
        status = (user.getStatus());
    }
}
