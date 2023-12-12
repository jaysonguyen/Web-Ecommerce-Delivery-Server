package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.BlackList;
import com.example.deliveryecommercebackend.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlackListDTO {
    private String id;
    private String des;
    private String code;
    private Boolean is_active;
    private String user_name;
    private String phone;
    private String user_id;


    public void setData(BlackList blackList, User user){
        des= (blackList.getDes());
        code= (blackList.getCode());
        is_active = (false);
        user_name = (user.getFullName());
        phone= (user.getPhone());
        user_id= (user.getUser_id());
    }
}
