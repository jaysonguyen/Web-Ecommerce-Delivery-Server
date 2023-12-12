package com.example.deliveryecommercebackend.DTO;


import com.example.deliveryecommercebackend.model.Branch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchDisplayDTO {
    private String branch_id;
    private String name;
    private String address;
    private String des;
    private String code;

    public void setData(Branch branch){
        branch_id = branch.getBranch_id();
        name = (branch.getName());
        des = (branch.getDes());
        address = (branch.getAddress());
        code = (branch.getCode());
    }
}
