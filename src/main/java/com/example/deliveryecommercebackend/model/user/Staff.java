package com.example.deliveryecommercebackend.model.user;

import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.model.Branch;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@Entity
public class Staff extends User {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    @ToString.Exclude
    private Branch branch;

    @Override
    public void setDataCreate(UserCreateDTO userDTO) {
        super.setDataCreate(userDTO);
        this.branch = userDTO.getBranch() != null ? userDTO.getBranch() : null;
//        this.setBranch(userDTO.getBranch());
    }
}
