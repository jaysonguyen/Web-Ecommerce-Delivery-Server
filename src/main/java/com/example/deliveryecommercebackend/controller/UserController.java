package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.example.deliveryecommercebackend.services.AuthenticationServices;
import com.example.deliveryecommercebackend.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.PostUpdate;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;

//@CrossOrigin(origins = "*", allowCredentials = "true")
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
                return ResponseEntity.ok().body(listUser);
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }

    }
    @GetMapping("{user_id}")
    public ResponseEntity<?>getUserById(@PathVariable String user_id) {
        try {
            var user = userService.getUserById(user_id);
            if (user.getId() == null) {
                return ResponseEntity.ok().body("User not found.");
            } else {
                return ResponseEntity.ok().body(user);
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }

    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        try {
            HttpStatus checkAdd = userService.createUser(user);
            if(checkAdd == HttpStatus.OK) {
                return ResponseEntity.ok("Insert success");
            } else {
                return ResponseEntity.status(checkAdd).body("Insert user failed");
            }
        } catch (Exception ex) {
            System.out.println("Error from server, Error:" + ex);
            return ResponseEntity.badRequest().body("Error from user");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUserFromAdmin(@RequestBody UserDTO user) {
        try {
            HttpStatus check = userService.updateUserAdmin(user);
            if(check != HttpStatus.OK)
                return ResponseEntity.status(check).body("Update data failed");
            return ResponseEntity.status(check).body("Update data successfully");
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex);
            return ResponseEntity.badRequest().body("Error fom server" + ex);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(String account) {
        try {
            HttpStatus check = userService.deleteUser(account);
            if (check == HttpStatus.OK) {
                return ResponseEntity.status(check).body("Delete user success");
            }
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete user failed");
    }

}
