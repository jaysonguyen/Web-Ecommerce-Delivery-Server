package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    private String id;
    private String code;
    private String fullName;
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

    private double cod;
    private int point;

    public UserCreateDTO(User user) {
        this.id = user.getUser_id();
        this.code = user.getCode();
        this.fullName = user.getFullName();
        this.des = user.getDes();
        this.created = Date.valueOf(LocalDate.now());
        this.updated = Date.valueOf(LocalDate.now());
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
        code = user.getCode();
        fullName = (user.getFullName());
        des = (user.getDes());
        created = Date.valueOf(LocalDate.now());
        updated = Date.valueOf(LocalDate.now());
        isDelete = (user.is_delete());
        email = (user.getEmail());
        roleName = (user.getRole().getName());
        major = (user.getMajor());
        scale = (user.getScale());

        cod = 0;
        point = 0;
    }

}
