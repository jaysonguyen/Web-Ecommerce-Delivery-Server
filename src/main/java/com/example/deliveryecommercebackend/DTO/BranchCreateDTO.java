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
public class BranchCreateDTO {
    private String branch_id;
    private String city_code;
    private String name;
    private String address;
    private String des;
    private boolean is_delete;
    private String code;

//    public void setData(Branch branch){
//        branch_id = branch.getBranch_id();
//        city_id = branch.getCity().getId();
//        name = (branch.getName());
//        des = (branch.getDes());
//        address = (branch.getAddress());
//        code = (branch.getCode());
//        is_delete = (false);
//    }
}
