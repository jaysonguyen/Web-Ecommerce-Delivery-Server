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
public class BranchDropdownDTO {
    private String content;
    private String code;

    public void setData(Branch branch){
       content = (branch.getName());
       code = (branch.getCode());
    }
}
