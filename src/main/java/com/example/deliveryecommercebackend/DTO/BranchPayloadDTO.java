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
public class BranchPayloadDTO {
    private String branch_id;
    private String name;

    public BranchPayloadDTO(Branch branch) {
            branch_id = branch.getBranch_id();
            name = (branch.getName());
    }

    public void setData(Branch branch){
        branch_id = branch.getBranch_id();
        name = (branch.getName());
    }
}
