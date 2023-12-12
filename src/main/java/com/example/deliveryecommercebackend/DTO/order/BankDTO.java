package com.example.deliveryecommercebackend.DTO.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {

    private String bank_id;
    private String name;
    private boolean state;

}
