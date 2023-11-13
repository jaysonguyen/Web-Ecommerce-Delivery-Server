package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.example.deliveryecommercebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/store/{userId}")
//    @ResponseBody
//    public ResponseEntity<?> getStoreList(@PathVariable String userId) {
//        try {
//            var storeList = userService.getStoreByUser(userId);
//            if(storeList != null) {
//                return ResponseEntity.ok(storeList);
//            }
//        } catch (Exception ex) {
//            System.out.printf("Error from server");
//        }
//        return ResponseEntity.badRequest().body("Get list store failed");
//    }

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
    public ResponseEntity<?> createUser(@RequestBody UserCreateDTO user) {
        try {
            HttpStatus checkAdd = userService.createUser(user);
            if(checkAdd == HttpStatus.OK) {
                return ResponseEntity.ok("Insert success");
            } else {
                return ResponseEntity.status(checkAdd).body("Insert user failed");
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server: " + ex);
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

    @PutMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            HttpStatus check = userService.deleteUser(id);
            if (check == HttpStatus.OK) {
                return ResponseEntity.status(check).body("Delete user success");
            }
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete user failed");
    }


    @GetMapping("/staff")
    @ResponseBody
    public ResponseEntity<?> getStaff() {
        try {
            var getListStaff = userService.getStaff();
            return (getListStaff);

        } catch (Exception ex) {
            System.out.println("Error from controller");
            return ResponseEntity.badRequest().body("Error from controller" + ex);
        }
    }

    @GetMapping("/shipper")
    @ResponseBody
    public ResponseEntity<?> getShipper() {
        try {
            var getListStaff = userService.getShipper();
            return (getListStaff);

        } catch (Exception ex) {
            System.out.println("Error from controller");
            return ResponseEntity.badRequest().body("Error from controller" + ex);
        }
    }

    @GetMapping("/customer")
    @ResponseBody
    public ResponseEntity<?> getCustomer() {
        try {
            var getListStaff = userService.getCustomer();
            return (getListStaff);

        } catch (Exception ex) {
            System.out.println("Error from controller");
            return ResponseEntity.badRequest().body("Error from controller" + ex);
        }
    }

}
