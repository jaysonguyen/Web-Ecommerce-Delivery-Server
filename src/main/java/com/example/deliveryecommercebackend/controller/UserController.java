package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    public List<User>getAllUser() {
        List<User> userList = userService.getAllUsers();
        return userList;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        boolean checkAdd = userService.createUser(user);
        if(!checkAdd) {
            return ResponseEntity.ofNullable("Insert failed");
        }
        return ResponseEntity.ok("Insert success");


    }


}
