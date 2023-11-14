package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class getUserListDTO {
    private String code;
    private String fullName;
    private String account;
    private String email;
    private String roleName;
    private String phoneNumber;
    private String des;
    private Date updated;
    private String major;
    private String scale;
    private double cod;
    private int point;


    public void setData(User user){
        code = user.getCode();
        fullName = (user.getFullName());
        updated = (user.getUpdated());
        email = (user.getEmail());
        roleName = (user.getRole().getName());
        account = (user.getAccount());
        phoneNumber = (user.getPhone());
        des = (user.getDes());
        major = user.getMajor();
        scale = user.getScale();
        cod = user.getCod();
        point = user.getPoint();
    }

}
