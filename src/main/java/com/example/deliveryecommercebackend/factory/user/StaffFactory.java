package com.example.deliveryecommercebackend.factory.user;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.model.user.User;
import com.example.deliveryecommercebackend.model.user.Staff;

public class StaffFactory implements UserFactory {
    @Override
    public User createUser(UserCreateDTO userCreateDTO) {
        Staff newStaff = new Staff();

        newStaff.setDataCreate(userCreateDTO);

        return newStaff;
    }
}
