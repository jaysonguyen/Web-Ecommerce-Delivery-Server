package com.example.deliveryecommercebackend.model;

import com.example.deliveryecommercebackend.DTO.BranchCreateDTO;
import com.example.deliveryecommercebackend.model.ShippingAssignment;

import com.example.deliveryecommercebackend.model.ShippingAssignment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String branch_id;
    private String name;
    private String address;
    private String des;
    @JsonIgnore
    private boolean is_delete;
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @OneToOne
    @JoinColumn(name = "city_id")
//    @JsonBackReference
    @JsonManagedReference
    private City city;

    @OneToMany(mappedBy = "branch")
    @JsonBackReference
    private List<ShippingAssignment> shippingAssignments;

    public void setCreate(BranchCreateDTO branchDTO, City city){
        branch_id = branchDTO.getBranch_id();
        name = (branchDTO.getName());
        des = (branchDTO.getDes());
        address = (branchDTO.getAddress());
        code = (branchDTO.getCode());
        this.city = city;
    }

}
