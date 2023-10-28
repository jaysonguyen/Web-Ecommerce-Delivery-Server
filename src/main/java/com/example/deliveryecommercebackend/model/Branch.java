package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.DTO.BranchDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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
    private boolean is_delete;

}
