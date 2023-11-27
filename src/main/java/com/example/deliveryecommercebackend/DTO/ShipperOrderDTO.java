package com.example.deliveryecommercebackend.DTO;


import com.example.deliveryecommercebackend.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ShipperOrderDTO {

    private String customerName;
    private Order orders;
}
