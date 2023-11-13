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
    private String code;
    private String fullName;
    private String des;
    private String account;
    private String password;
    private String email;
    private String phone;
    private String purpose;
    private int role;
//    private String major;
//    private String scale;

    private double cod;
    private int point;


}
