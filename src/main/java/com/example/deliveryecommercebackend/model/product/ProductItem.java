package com.example.deliveryecommercebackend.model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductItem implements ProductComponent {
    private String id;
    private String code;
    private String name;
    private String weight;
    private String quantity;
    private String product_type;

    @Override
    public void deliver() {
        System.out.println("delivery product: " + name);
    }
}
