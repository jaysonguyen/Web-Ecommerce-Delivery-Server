package com.example.deliveryecommercebackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaDTO {
    private String id;
    private String name;
    private String des;
    private boolean type;
}
