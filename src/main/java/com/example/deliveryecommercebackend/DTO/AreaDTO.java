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
public class AreaDTO {
    private String id;
    private String code;
    private String name;
    private String des;

    public AreaDTO(Area area) {
        this.id = area.getId();
        this.code = area.getCode();
        this.name = area.getName();
        this.des = area.getDes();
    }
}
