package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Bank;
import com.example.deliveryecommercebackend.model.BlackList;
import com.example.deliveryecommercebackend.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDropdownDTO {
    private String code;
    private String content;


    public BankDropdownDTO(Bank bank){
        code= (bank.getBank_id());
        content = (bank.getName());
    }
}
