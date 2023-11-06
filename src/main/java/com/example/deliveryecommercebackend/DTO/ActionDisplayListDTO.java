package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Action;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionDisplayListDTO {

    private String code;
    private String name;

    public ActionDisplayListDTO(Action action) {
        this.code = action.getCode();
        this.name = action.getName();
    }
}
