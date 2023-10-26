package com.example.deliveryecommercebackend.model;


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

@Table(name = "role")
public class Role {

    @Id
    @Column(name = "id")
    private int role_id;

    @Column(name = "name")
    private String name;

    @Column(name = "des")
    private String des;

    @OneToMany(mappedBy = "role")
    @JsonManagedReference
    private List<User> users;
}
