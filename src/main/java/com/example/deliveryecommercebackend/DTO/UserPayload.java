package com.example.deliveryecommercebackend.DTO;


import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.Role;
import com.example.deliveryecommercebackend.model.User;
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
    private BranchPayloadDTO branch;

    public UserPayload (User user) {
        this.userID = user.getUser_id();
        this.userCode = user.getCode();
        this.role = user.getRole().getName();
        if(user.getBranch() != null) {
            this.branch = new BranchPayloadDTO(user.getBranch());
        }
    }

}
