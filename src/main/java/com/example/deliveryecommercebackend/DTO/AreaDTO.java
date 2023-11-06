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
    private String code;
    private String content;

    public AreaDTO(Area area) {
        this.code = area.getCode();
        this.content = area.getName();
    }
}
