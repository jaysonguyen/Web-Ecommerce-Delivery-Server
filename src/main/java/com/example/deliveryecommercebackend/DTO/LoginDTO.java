package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String account;
    private String password;

}
