package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="history_voucher")
public class HistoryVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String history_id;
    private String order_id;
    private String voucher_id;
    private Date date_time;
    //user name
    private String input_by;

    public HistoryVoucher(String voucherId, String orderId, User user) {
        this.voucher_id = voucherId;
        this.order_id = orderId;
        this.date_time = new Date();
        this.input_by = user.getFullName();
    }
}
