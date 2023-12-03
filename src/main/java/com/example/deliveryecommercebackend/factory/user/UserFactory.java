package com.example.deliveryecommercebackend.factory.user;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.model.user.Admin;
import com.example.deliveryecommercebackend.model.user.Customer;
import com.example.deliveryecommercebackend.model.user.Shipper;
import com.example.deliveryecommercebackend.model.user.Staff;
import com.example.deliveryecommercebackend.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserFactory {

    User createUser(UserCreateDTO userCreateDTO);
}

