package com.example.deliveryecommercebackend.model.user;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.model.Area;
import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.Role;
import com.example.deliveryecommercebackend.model.User;
import jakarta.persistence.Entity;

import java.util.Collections;
import java.util.List;

@Entity
public class Staff extends User {
    @Override
    public void setDataCreate(UserCreateDTO userDTO) {
        super.setDataCreate(userDTO);
        this.setBranch(userDTO.getBranch());
    }
}
