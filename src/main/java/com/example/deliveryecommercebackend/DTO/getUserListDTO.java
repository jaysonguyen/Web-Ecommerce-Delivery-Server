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
    private String fullName;
    private String account;
    private String email;
    private String roleName;
    private Date updated;

    public void setData(User user){
        id = user.getUser_id();
        fullName = (user.getFullName());
        updated = (user.getUpdated());
        email = (user.getEmail());
        roleName = (user.getRole().getName());
        account = (user.getAccount());
    }

}
