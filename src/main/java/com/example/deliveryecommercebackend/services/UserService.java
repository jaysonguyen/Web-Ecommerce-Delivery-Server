package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.example.deliveryecommercebackend.model.Role;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.repository.RoleRepository;
import com.example.deliveryecommercebackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        try {
            List<User> users = userRepository.findNoneDeleteUser();
            return users;
        } catch(Exception ex) {
            System.out.printf("Get user failed - Error: " + ex);
            return Collections.emptyList();
        }
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
            System.out.printf("Create uesr failed - Error" + ex);
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

}
