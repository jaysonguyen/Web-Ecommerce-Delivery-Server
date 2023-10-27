package com.example.deliveryecommercebackend.DTO;

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
    private String name;
    private boolean isDeleted;
    //percent
    private int cost;
    private Date created;
    private int period;
    private int quantity;
    private int used;
    private String status;

}
