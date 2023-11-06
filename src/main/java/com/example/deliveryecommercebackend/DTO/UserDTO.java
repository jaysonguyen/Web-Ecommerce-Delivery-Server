package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Role;
import com.example.deliveryecommercebackend.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String fullName;
    private String code;
    private String des;
    private Date created;
    private Date updated;
    private boolean isDelete;
    private String account;
    private String password;
    private String email;
    private String phone;
    private String purpose;
    private int role;
    private String roleName;
    private String major;
    private String scale;

    public UserDTO(User user) {
        this.id = user.getUser_id();
        this.fullName = user.getFullName();
        this.des = user.getDes();
        this.created = user.getCreated();
        this.updated = user.getUpdated();
        this.account = user.getAccount();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.purpose = user.getPurpose();
        this.role = user.getRole().getRoleId();
        this.roleName = user.getRole().getName();
        this.major = user.getMajor();
        this.scale = user.getScale();
    }

    public void setData(User user){
        id = user.getUser_id();
        fullName = (user.getFullName());
        des = (user.getDes());
        created = (user.getCreated());
        updated = (user.getUpdated());
        isDelete = (user.is_delete());
        email = (user.getEmail());
        roleName = (user.getRole().getName());
        major = (user.getMajor());
        scale = (user.getScale());
    }

}
