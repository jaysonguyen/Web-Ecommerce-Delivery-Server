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
    private String id;
    private String account;
    private String email;
    private String phoneNumber;
    private String des;

    public void setData(User user){
        id = user.getUser_id();
        email = (user.getEmail());
        account = (user.getAccount());
        phoneNumber = (user.getPhone());
        des = (user.getDes());
    }

}
