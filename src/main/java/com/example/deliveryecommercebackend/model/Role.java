package com.example.deliveryecommercebackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne(mappedBy = "role") // Use "role" instead of "user"
    private User user;
}
