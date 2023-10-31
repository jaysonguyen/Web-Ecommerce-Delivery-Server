package com.example.deliveryecommercebackend.DTO;


import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {
    private String branch_id;
    private String name;
    private String address;
    private String des;
    private boolean is_delete;

    public void setData(Branch branch){
        branch_id = branch.getBranch_id();
        name = (branch.getName());
        des = (branch.getDes());
        address = (branch.getAddress());
        is_delete = (false);
    }
}
