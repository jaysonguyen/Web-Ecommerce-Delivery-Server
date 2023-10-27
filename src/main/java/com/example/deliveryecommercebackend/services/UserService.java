package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return users;
        } catch(Exception ex) {
            System.out.printf("Get user failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public boolean createUser(UserDTO user) {
        User newUser = new User();
        newUser.setCreated(Date.valueOf(LocalDate.now()));
        newUser.setUpdated(Date.valueOf(LocalDate.now()));
        newUser.setAccount(user.getAccount());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        newUser.setPhone(user.getPhone());
        newUser.setDes(user.getDes());
        newUser.setFullName(user.getFullName());
        newUser.setPurpose(user.getPurpose());

        try {
            User checkSave = userRepository.save(newUser);
            if(checkSave != null) {
                return true;
            }
            return false;
        } catch(Exception ex) {
            System.out.printf("Create uesr failed - Error" + ex);
            return false;
        }
    }

}
