package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.LoginDTO;
import com.example.deliveryecommercebackend.DTO.UserPayload;
import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.repository.BranchRepository;
import com.example.deliveryecommercebackend.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServices {

    @Autowired
    private UserRepository userRepository;

    private BranchRepository branchRepo;

    public AuthenticationServices(UserRepository userRepository, BranchRepository branchRepo) {
        this.userRepository = userRepository;
        this.branchRepo = branchRepo;
    }

    public UserPayload loginUser(LoginDTO loginDTO) {
        User user = userRepository.findUserByAccount(loginDTO.getAccount());
        UserPayload userPl = new UserPayload(user);
        if(user == null) {
            return null;
        }
        var parsePass = BCrypt.checkpw(loginDTO.getPassword(), user.getPassword());
        if(parsePass == false) {
            return null;
        }
        return userPl;
    }

}
