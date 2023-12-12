package com.example.deliveryecommercebackend.DTO.report;

import com.example.deliveryecommercebackend.model.product.ProductComponent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.example.deliveryecommercebackend.model.product.OrderProduct;
import com.example.deliveryecommercebackend.model.product.ProductItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
public class ProductGetList {
    private Date create;
    private OrderProduct product;

    public ProductGetList(Date create, String product) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dt = new SimpleDateFormat("MM-dd-yyyy");
        String currDate = dt.format(create);

        this.create = create;
        this.product = createOrderProduct(product);
    }

    private OrderProduct createOrderProduct(String productJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ProductItem> productItemList = objectMapper.readValue(productJson, new TypeReference<List<ProductItem>>() {});

            OrderProduct orderProduct = new OrderProduct();
            orderProduct.addAllItem(productItemList);

            return orderProduct;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately, maybe log it or throw a custom exception.
            return null; // or throw a custom exception
        }
    }
}
