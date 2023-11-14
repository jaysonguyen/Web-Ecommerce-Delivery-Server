package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.example.deliveryecommercebackend.DTO.getUserListDTO;
import com.example.deliveryecommercebackend.model.Role;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private BranchRepository branchRepo;

    @Autowired
    private ShippingAssignmentRepository shipRepo;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BranchRepository branchRepo, ShippingAssignmentRepository shipRepo) {
        this.userRepository = userRepository;
        this.branchRepo = branchRepo;
        this.shipRepo = shipRepo;
        this.roleRepository = roleRepository;
    }

    public List<getUserListDTO> getAllUsers() {
        try {
            var staffList = userRepository.findNoneDeleteUser();
            List<getUserListDTO> res = new ArrayList<getUserListDTO>();
            for(User user : staffList){
                getUserListDTO temp = new getUserListDTO();
                temp.setData(user);
                res.add(temp);
            }

            return res;
        } catch(Exception ex) {
            System.out.printf("Get user failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public UserDTO getUserById(String id) {
        try {
            User user = userRepository.findUserById(id);
            return new UserDTO(user);
        } catch(Exception ex) {
            System.out.printf("Get user failed - Error: " + ex);
            return new UserDTO();
        }
    }


//    public List<Store> getStoreByUser(String userId) {
//        try {
//            var storeList = storeRepository.findStoreByUser(userId);
//            return storeList;
//        } catch (Exception ex) {
//            System.out.printf("Error from services: " + ex.getMessage());
//        }
//
//        return Collections.emptyList();
//    }

    public HttpStatus createUser(UserCreateDTO userDTO) {
        Role role = roleRepository.findById(userDTO.getRole()).get();
        System.out.println(role.toString());

        var checkValidAccount = userRepository.findUserByAccount(userDTO.getAccount());
        var checkValidEmail = userRepository.findUsersByEmail(userDTO.getEmail());

        // existing user
        if(checkValidEmail != null) {
            return HttpStatus.FOUND;
        }
        if(checkValidAccount != null) {
            return HttpStatus.FOUND;
        }
        User newUser = new User();
        newUser.setDataCreate(userDTO, role);

        try {
            User checkSave = userRepository.save(newUser);
            if(checkSave != null) {
                return HttpStatus.OK;
            } else {
                return HttpStatus.BAD_REQUEST;
            }
        } catch(Exception ex) {
            System.out.printf("Create user failed - Error" + ex);
            return HttpStatus.BAD_GATEWAY;
        }
    }

    public HttpStatus updateUserAdmin(UserDTO userDto) {
        var user = userRepository.findUserByAccount(userDto.getAccount());
        user.setFullName(userDto.getFullName());
        user.setPurpose(userDto.getPurpose());
        user.setPhone(userDto.getPhone());
        user.set_delete(userDto.isDelete());
        user.setDes(userDto.getDes());
        user.setMajor(userDto.getMajor());
        user.setScale(userDto.getScale());

        try {
            var checkSave = userRepository.save(user);
            if (checkSave != null)
                return HttpStatus.OK;
        }catch (Exception ex) {
            System.out.printf("Error from service" + ex);
        }
        return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus deleteUser(String account) {
        User user = userRepository.findUserById(account);
        user.set_delete(true);
        try {
            var checkUpdate = userRepository.save(user);
            if(checkUpdate == null) {
                return HttpStatus.CONFLICT;
            }
            return HttpStatus.OK;
        } catch (Exception ex) {
            System.out.printf("Error from service", ex);
            return HttpStatus.BAD_REQUEST;
        }

    }


//    STAFF
    public ResponseEntity<?> getStaff() {
        try {
            var roleStaff = roleRepository.findRoleByName("staff");

            if(roleStaff == null) {
                return ResponseEntity.badRequest().body("Not found role");
            }

            var staffList = userRepository.findUsersByRole(roleStaff);
            List<getUserListDTO> res = new ArrayList<getUserListDTO>();
            for(User user : staffList){
                getUserListDTO temp = new getUserListDTO();
                temp.setData(user);
                res.add(temp);
            }


            return ResponseEntity.ok().body(res);

        } catch (Exception ex) {
            System.out.println("Error from services" + ex);
            return ResponseEntity.badRequest().body("Error: " + ex);
        }
    }
    public ResponseEntity<?> getCustomer() {
        try {
            var roleStaff = roleRepository.findRoleByName("customer");

            if(roleStaff == null) {
                return ResponseEntity.badRequest().body("Not found role");
            }

            var staffList = userRepository.findUsersByRole(roleStaff);
            List<getUserListDTO> res = new ArrayList<getUserListDTO>();
            for(User user : staffList){
                getUserListDTO temp = new getUserListDTO();
                temp.setData(user);
                res.add(temp);
            }

            return ResponseEntity.ok().body(res);

        } catch (Exception ex) {
            System.out.println("Error from services" + ex);
            return ResponseEntity.badRequest().body("Error: " + ex);
        }
    }
    public ResponseEntity<?> getShipper() {
        try {
            var roleStaff = roleRepository.findRoleByName("shipper");

            if(roleStaff == null) {
                return ResponseEntity.badRequest().body("Not found role");
            }

            var staffList = userRepository.findUsersByRole(roleStaff);
            List<getUserListDTO> res = new ArrayList<getUserListDTO>();
            for(User user : staffList){
                getUserListDTO temp = new getUserListDTO();
                temp.setData(user);
                res.add(temp);
            }

            return ResponseEntity.ok().body(res);

        } catch (Exception ex) {
            System.out.println("Error from services" + ex);
            return ResponseEntity.badRequest().body("Error: " + ex);
        }
    }

    public ResponseEntity<?> getAssignmentShipperInfo(String branchID) {
        try {
            var branch = branchRepo.findById(branchID).get();
            System.out.println(branch);
            var assignList = shipRepo.findShippingAssignmentByBranch(branch);
            System.out.printf(assignList.toString());
            return ResponseEntity.ok().body(assignList);
        }
        catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

    public ResponseEntity<?> getShipperByBranch(String branchCode) {
        try {

            var roleStaff = roleRepository.findRoleByName("shipper");

            if(roleStaff == null) {
                return ResponseEntity.badRequest().body("Not found role");
            }

            var branch = branchRepo.findBranchByCode(branchCode);
           var shipperList = userRepository.findShipperByBranch(branch, roleStaff);
            System.out.println(roleStaff.getName());

            List<getUserListDTO> res = new ArrayList<>();
            for(User user : shipperList){
                getUserListDTO temp = new getUserListDTO();
                temp.setData(user);
                res.add(temp);
            }

            return ResponseEntity.ok().body(res);

        }catch (Exception ex) {
            System.out.println("Error from services: " + ex);
            return ResponseEntity.badRequest().body("Error from services" + ex);
        }
    }



}
