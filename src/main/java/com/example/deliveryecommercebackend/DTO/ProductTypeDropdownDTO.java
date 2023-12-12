package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeDropdownDTO {
    private String code;
    private String content;

    public ProductTypeDropdownDTO(ProductType productType) {
        this.code = productType.getId();
        this.content = productType.getName();
    }
}
