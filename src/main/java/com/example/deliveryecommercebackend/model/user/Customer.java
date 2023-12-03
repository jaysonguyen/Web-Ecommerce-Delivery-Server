package com.example.deliveryecommercebackend.model.user;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.model.User;
import jakarta.persistence.Entity;

@Entity
public class Customer extends User {
    @Override
    public void setDataCreate(UserCreateDTO userDTO) {
        super.setDataCreate(userDTO);

    }
}
