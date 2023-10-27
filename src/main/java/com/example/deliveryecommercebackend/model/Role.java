package com.example.deliveryecommercebackend.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity

@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "des")
    private String des;

//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
//    @JsonManagedReference
//
//    private List<User> user;

}
