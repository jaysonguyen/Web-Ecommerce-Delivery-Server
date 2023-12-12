package com.example.deliveryecommercebackend.factory.user;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.model.user.Admin;
import com.example.deliveryecommercebackend.model.user.User;

public class AdminFactory implements UserFactory {
    @Override
    public User createUser(UserCreateDTO userCreateDTO) {
        Admin newAdmin = new Admin();
        newAdmin.setDataCreate(userCreateDTO);
        return newAdmin;
    }
}
