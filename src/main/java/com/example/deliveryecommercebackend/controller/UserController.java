package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.example.deliveryecommercebackend.services.AuthenticationServices;
import com.example.deliveryecommercebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    @ResponseBody
    public ResponseEntity<?>getAllUser() {
        try {
            var listUser = userService.getAllUsers();
            if (listUser.isEmpty()) {
                return ResponseEntity.ok().body("Empty list user.");
            } else {
                return ResponseEntity.ok(listUser);
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }

    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        try {
            boolean checkAdd = userService.createUser(user);
            if(!checkAdd) {
                return ResponseEntity.badRequest().body("Insert data failed");
            }
            return ResponseEntity.ok("Insert success");
        } catch (Exception ex) {
            System.out.println("Error from server, Error:" + ex);
            return ResponseEntity.badRequest().body("Error from user");
        }
    }


}
