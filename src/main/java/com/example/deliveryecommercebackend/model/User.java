package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Resource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.id.GUIDGenerator;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String user_id;

    private String fullName;
    private String des;
    private Date created;
    private Date updated;
    private boolean is_delete;
    private String account;
    private String password;
    private String email;
    private String phone;
    private String purpose;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Store> store;


}
