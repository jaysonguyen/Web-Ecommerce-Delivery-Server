package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Resource;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.id.GUIDGenerator;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")

    @ToString.Exclude
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Store> stores;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> orders;
}
