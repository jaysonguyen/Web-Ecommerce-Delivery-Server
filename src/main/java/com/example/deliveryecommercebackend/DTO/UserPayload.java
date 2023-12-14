package com.example.deliveryecommercebackend.DTO;


import com.example.deliveryecommercebackend.model.user.Staff;
import com.example.deliveryecommercebackend.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPayload {

    private String userID;
    private String userCode;
    private String role;
    private int point;
    private double cod;
    private BranchPayloadDTO branch;

    public UserPayload (User user) {
        this.userID = user.getUser_id();
        this.userCode = user.getCode();
        this.point = user.getPoint();
        this.cod = user.getCod();
        this.role = user.getRole().getName();
        if( user instanceof Staff && ((Staff) user).getBranch() != null) {
            this.branch = new BranchPayloadDTO(((Staff) user).getBranch());
        }
    }

}
