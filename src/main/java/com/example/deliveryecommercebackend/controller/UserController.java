package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.ShipperAssignmentDTO;
import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.example.deliveryecommercebackend.model.ShippingAssignment;
import com.example.deliveryecommercebackend.services.AuthenticationServices;
import com.example.deliveryecommercebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("{userId}/store")
    @ResponseBody
    public ResponseEntity<?> getStoreList(@PathVariable String userId) {
        try {
            var storeList = userService.getStoreByUser(userId);
            return storeList;
        } catch (Exception ex) {
            System.out.printf("Error from server");
            return ResponseEntity.badRequest().body("Error from controller: " + ex.getMessage());
        }
    }

    @GetMapping("{user_code}")
    public ResponseEntity<?>getUserByCode(@PathVariable String user_code) {
        try {
            var user = userService.getUserByCode(user_code);
            return user;
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from controller: " + ex.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreateDTO user) {
        try {
            var checkAdd = userService.createUser(user);
            return checkAdd;
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

    @GetMapping("/shipper/assignment/{branchID}")
    @ResponseBody
    public ResponseEntity<?> getShippingAssignment(@PathVariable String branchID) {
        try {
            var getShippingInfo = userService.getAssignmentShipperInfo(branchID);
            return getShippingInfo;
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from controller");
        }
    }

    @GetMapping("/shipper/branch/{branchID}")
    @ResponseBody
    public ResponseEntity<?> getShipperByBranch(@PathVariable String branchID) {
        try {
            var getListShipperByBranch = userService.getShipperByBranch(branchID);
            return getListShipperByBranch;
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from controller");
        }
    }


    @PostMapping("/shipper/assignment/{area_code}/{branch_code}/{user_code}")
    public ResponseEntity<?> setShippingAssignment(@PathVariable String area_code, @PathVariable String branch_code, @PathVariable String user_code) {
        try {
            var setShipment = userService.setAssignmentShipment(area_code, branch_code, user_code);
            return setShipment;
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from controller");
        }
    }

    @DeleteMapping("/shipper/assignment/{area_code}/{branch_code}")
    public ResponseEntity<?> deleteShippingAssignment(@PathVariable String area_code, @PathVariable String branch_code) {
        try {
            var setShipment = userService.deleteShipment(area_code, branch_code);
            return setShipment;
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from controller");
        }
    }
}
