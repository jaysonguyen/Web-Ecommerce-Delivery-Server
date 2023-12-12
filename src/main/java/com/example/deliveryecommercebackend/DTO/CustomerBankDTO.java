package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Area;
import com.example.deliveryecommercebackend.model.CustomerBank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBankDTO {
    private String user_id;
    private String bank_name;
    private String bank_number;

    public CustomerBankDTO(CustomerBank customerBank) {
        this.user_id = customerBank.getUser_id();
        this.bank_name = customerBank.getBank_name();
        this.bank_number = customerBank.getBank_number();
    }
}
