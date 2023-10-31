package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.example.deliveryecommercebackend.DTO.getUserListDTO;
import com.example.deliveryecommercebackend.model.Role;
import com.example.deliveryecommercebackend.model.Store;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.repository.RoleRepository;
import com.example.deliveryecommercebackend.repository.StoreRepository;
import com.example.deliveryecommercebackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
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

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
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


    public List<Store> getStoreByUser(String userId) {
        try {
            var storeList = storeRepository.findStoreByUser(userId);
            return storeList;
        } catch (Exception ex) {
            System.out.printf("Error from services: " + ex.getMessage());
        }

        return Collections.emptyList();
    }

    public HttpStatus createUser(UserDTO user) {
        Role role = roleRepository.findRoleByRoleId(user.getRole());
        if(role == null) {
            role = roleRepository.findRoleByRoleId(1);
        }
        var checkValidAccount = userRepository.findUserByAccount(user.getAccount());
        var checkValidEmail = userRepository.findUsersByEmail(user.getEmail());
        if(checkValidEmail != null) {
            return HttpStatus.FOUND;
        }
        if(checkValidAccount != null) {
            return HttpStatus.FOUND;
        }
        User newUser = new User();
        newUser.setCreated(Date.valueOf(LocalDate.now()));
        newUser.setUpdated(Date.valueOf(LocalDate.now()));
        newUser.setAccount(user.getAccount());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        newUser.setPhone(user.getPhone());
        newUser.setDes(user.getDes());
        newUser.setFullName(user.getFullName());
        newUser.setPurpose(user.getPurpose());
        newUser.setRole(role);

        try {
            User checkSave = userRepository.save(newUser);
            if(checkSave != null) {
                return HttpStatus.OK;
            }
        } catch(Exception ex) {
            System.out.printf("Create user failed - Error" + ex);
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public HttpStatus updateUserAdmin(UserDTO userDto) {
        var user = userRepository.findUserByAccount(userDto.getAccount());
        user.setFullName(userDto.getFullName());
        user.setPurpose(userDto.getPurpose());
        user.setPhone(userDto.getPhone());
        user.set_delete(userDto.isDelete());
        user.setDes(userDto.getDes());

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
        var user = userRepository.findUserByAccount(account);
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


}
