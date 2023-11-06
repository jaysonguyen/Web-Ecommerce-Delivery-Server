package com.example.deliveryecommercebackend.DTO.order;

import com.example.deliveryecommercebackend.model.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {
    private String id;
    private String code;
    private String name;
    private String des;

    public CityDTO(City city) {
            this.code = city.getCode();
            this.name = city.getName();
            this.des = city.getDes();
    }
}
