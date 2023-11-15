package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDropdownDTO {
    private String code;
    private String content;

    public CityDropdownDTO(City city) {
        this.code = city.getCode();
        this.content = city.getName();
    }
}
