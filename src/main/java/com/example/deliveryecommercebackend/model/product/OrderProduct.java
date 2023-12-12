package com.example.deliveryecommercebackend.model.product;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class OrderProduct implements ProductComponent{
    private List<ProductComponent> productItemList = new ArrayList<>();
    public void addItem(ProductComponent component) {
        productItemList.add(component);
    }
    public void addAllItem(List<? extends ProductComponent> components) {
        productItemList.addAll(components);
    }
    @Override
    public void deliver() {

    }
}
