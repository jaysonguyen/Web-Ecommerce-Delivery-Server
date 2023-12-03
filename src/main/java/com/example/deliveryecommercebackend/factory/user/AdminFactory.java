package com.example.deliveryecommercebackend.factory.user;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.model.User;

public class AdminFactory implements UserFactory {
    @Override
    public User createUser(UserCreateDTO userCreateDTO) {
        return null;
    }
}
