package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Role;

public class LoginDTO {

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private String account;
    private String password;
    private Role role;

    public LoginDTO() {
    }

    public LoginDTO(String account, String password, Role role) {
        this.account = account;
        this.password = password;
        this.role = role;
    }

    public LoginDTO(String account, String password) {
        this.account = account;
        this.password = password;
    }



}
