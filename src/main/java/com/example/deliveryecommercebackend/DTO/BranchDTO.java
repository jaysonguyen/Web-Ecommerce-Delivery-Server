package com.example.deliveryecommercebackend.DTO;


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
}
