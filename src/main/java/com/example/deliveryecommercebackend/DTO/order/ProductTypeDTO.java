package com.example.deliveryecommercebackend.DTO.order;

import com.example.deliveryecommercebackend.model.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeDTO {
    private String id;
    private String code;
    private String name;
    private String des;

    public ProductTypeDTO(ProductType productType) {
        this.id = productType.getId();
        this.code = productType.getCode();
        this.name = productType.getName();
        this.des = productType.getDes();
    }
}
