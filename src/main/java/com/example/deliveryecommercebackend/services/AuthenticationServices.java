package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.LoginDTO;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServices {

    @Autowired
    private UserRepository userRepository;

    public AuthenticationServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean loginUser(LoginDTO loginDTO) {
        User user = userRepository.findUserByAccount(loginDTO.getAccount());
        if(user == null) {
            return false;
        }
        var parsePass = BCrypt.checkpw(loginDTO.getPassword(), user.getPassword());
        if(parsePass == false) {
            return false;
        }
        return true;
    }

}
