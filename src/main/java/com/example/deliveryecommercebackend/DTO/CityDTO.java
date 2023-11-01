package com.example.deliveryecommercebackend.DTO;

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
    private Date updated;
    private String type;

    public CityDTO(City city) {
        this.id = city.getId();
        this.code = city.getCode();
        this.name = city.getName();
        this.des = city.getDes();
        this.updated = city.getUpdated();
        this.type = city.getType();
    }
}
