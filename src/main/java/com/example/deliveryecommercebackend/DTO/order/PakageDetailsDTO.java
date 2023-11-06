package com.example.deliveryecommercebackend.DTO.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PakageDetailsDTO {
    String total_weight;
    double height;
    double length;
    double width;
    double cod;
    double cost;
    double cost_failed;
}
