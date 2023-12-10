package com.example.deliveryecommercebackend.factory.user;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.model.user.Shipper;
import com.example.deliveryecommercebackend.model.user.User;

public class ShipperFactory implements UserFactory {
    @Override
    public Shipper createUser(UserCreateDTO userCreateDTO) {
        Shipper newShipper = new Shipper();
        newShipper.setDataCreate(userCreateDTO);
        return newShipper;
    }
}
