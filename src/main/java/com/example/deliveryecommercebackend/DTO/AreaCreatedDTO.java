package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Area;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaCreatedDTO {
    private String code;
    private String name;
    private String des;
    private String city;

    public AreaCreatedDTO(Area area) {
        this.code = area.getCode();
        this.name = area.getName();
        this.des = area.getDes();
        this.city = area.getCity().getCode();
    }
}
