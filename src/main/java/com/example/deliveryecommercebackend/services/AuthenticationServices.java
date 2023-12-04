package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.LoginDTO;
import com.example.deliveryecommercebackend.DTO.UserPayload;
import com.example.deliveryecommercebackend.model.user.User;
import com.example.deliveryecommercebackend.repository.BranchRepository;
import com.example.deliveryecommercebackend.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> loginUser(LoginDTO loginDTO) {
        System.out.println(loginDTO.getAccount());
        User user = userRepository.findUserByAccount(loginDTO.getAccount());
        if(user == null) {
            return ResponseEntity.badRequest().body("Account not found");
        }
        var parsePass = BCrypt.checkpw(loginDTO.getPassword(), user.getPassword());
        if(parsePass == false) {
            return ResponseEntity.badRequest().body("Wrong password");
        }
        UserPayload userPl = new UserPayload(user);
        return ResponseEntity.ok().body(userPl);
    }

}
