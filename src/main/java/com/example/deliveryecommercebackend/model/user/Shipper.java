package com.example.deliveryecommercebackend.model.user;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.model.Branch;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Shipper extends User {
    private double shipment_salary = 0;
    private int shipmentPoint = 0;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    @ToString.Exclude
    private Branch branch;

    @ElementCollection
    private List<String> areas = new ArrayList<>();

    @Override
    public void setDataCreate(UserCreateDTO userDTO) {
        super.setDataCreate(userDTO);
        this.branch = userDTO.getBranch() != null ? userDTO.getBranch() : null;
    }
}

