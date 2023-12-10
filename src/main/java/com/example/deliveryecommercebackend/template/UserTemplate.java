package com.example.deliveryecommercebackend.template;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.DTO.getUserListDTO;
import com.example.deliveryecommercebackend.factory.user.AdminFactory;
import com.example.deliveryecommercebackend.factory.user.CustomerFactory;
import com.example.deliveryecommercebackend.factory.user.ShipperFactory;
import com.example.deliveryecommercebackend.factory.user.UserFactory;
import com.example.deliveryecommercebackend.model.user.Admin;
import com.example.deliveryecommercebackend.model.user.Shipper;
import com.example.deliveryecommercebackend.model.user.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class UserTemplate {
    protected abstract List<getUserListDTO> getAllUsers();
    protected abstract ResponseEntity<?> getUserByID(String id);
    protected abstract ResponseEntity<?> getUserByCode(String code);
    protected abstract ResponseEntity<?> create_customer(UserCreateDTO userDTO);
    protected abstract ResponseEntity<?> create_staff(UserCreateDTO userDTO);
    protected abstract User create_shipper(UserCreateDTO userDTO);
    protected abstract User create_admin(UserCreateDTO userDTO);
}
