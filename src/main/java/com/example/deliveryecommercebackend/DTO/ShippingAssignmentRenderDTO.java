package com.example.deliveryecommercebackend.DTO;


import com.example.deliveryecommercebackend.model.Area;
import com.example.deliveryecommercebackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ShippingAssignmentRenderDTO {
    private String areaName;
    private String user_name;
}
