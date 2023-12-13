package com.example.deliveryecommercebackend.model.user;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.model.Branch;
import jakarta.persistence.Entity;
import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Shipper extends User {
    private double shipment_salary = 0;
    private int shipment_point = 0;
    @ElementCollection
    private List<String> areas = new ArrayList<>();

    @Override
    public void setDataCreate(UserCreateDTO userDTO) {
        super.setDataCreate(userDTO);
        this.shipment_salary = 0;
    }

}

