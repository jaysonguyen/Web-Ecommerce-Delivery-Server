package com.example.deliveryecommercebackend.DTO.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    String name;
    String code;
    //gam
    double weight;
    int quantity;

    public ProductDTO fromJSON(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDTO res = objectMapper.readValue(json, this.getClass());

        return res;
    }

    public String toJson(ProductDTO productDTO) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(productDTO);
        return jsonString;
    }
}
