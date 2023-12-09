package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  UserCreateDTO {
    private String code;
    private String fullName;
    private String des;
    private String account;
    private String password;
    private String email;
    private String phone;
    private String purpose;
    private int role_id;
    private String branch_code;
//    private String major;
//    private String scale;

    private double cod;
    private int point;

    //branch
    @JsonIgnore
    Branch branch;
    @JsonIgnore
    Role role;

}
