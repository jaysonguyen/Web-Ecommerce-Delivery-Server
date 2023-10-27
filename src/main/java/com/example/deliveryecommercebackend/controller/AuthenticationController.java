package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.LoginDTO;
import com.example.deliveryecommercebackend.services.AuthenticationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/authenticaion")
public class AuthenticationController {

    @Autowired
    private AuthenticationServices authenticationServeces;

    public AuthenticationController(AuthenticationServices authenticationServeces) {
        this.authenticationServeces = authenticationServeces;
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginObject) {
        try {
            var checkLogin = authenticationServeces.loginUser(loginObject);
            if(checkLogin == false) {
                return ResponseEntity.status(404).body("Not found user");
            }
            return ResponseEntity.ok("Login success");
        }
        catch (Exception ex) {
            System.out.printf("Error from server" + ex);
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

}
