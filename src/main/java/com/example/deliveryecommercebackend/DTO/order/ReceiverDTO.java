package com.example.deliveryecommercebackend.DTO.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverDTO {
    String name;
    String phone;
    String address;
    String city;
    String area;
}
