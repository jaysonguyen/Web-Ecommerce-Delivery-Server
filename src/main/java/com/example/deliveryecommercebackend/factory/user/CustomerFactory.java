package com.example.deliveryecommercebackend.factory.user;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.model.Store;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.model.user.Customer;

import java.util.ArrayList;

public class CustomerFactory implements UserFactory {

    @Override
    public User createUser(UserCreateDTO userCreateDTO) {
        Customer newCustomer = new Customer();

        newCustomer.setDataCreate(userCreateDTO);
        Store defaultStore = new Store(newCustomer);
        newCustomer.setStores(new ArrayList<Store>(){
            {
                add(defaultStore);
            }
        });
        return newCustomer;
    }
}
