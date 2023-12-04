package com.example.deliveryecommercebackend.factory.user;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.model.user.User;

public interface UserFactory {

    User createUser(UserCreateDTO userCreateDTO);
}

