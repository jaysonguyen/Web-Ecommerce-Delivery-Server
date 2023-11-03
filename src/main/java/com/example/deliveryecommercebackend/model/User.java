package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Resource;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.id.GUIDGenerator;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;
import java.time.LocalDate;
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

    @Column(name = "code", unique = true, nullable = false)
    private String code;
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
    private String major;
    private String scale;

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

    public void setDataCreate(UserCreateDTO userDTO, Role role){
        this.created = (Date.valueOf(LocalDate.now()));
        this.updated = (Date.valueOf(LocalDate.now()));
        this.account = (userDTO.getAccount());
        this.email = (userDTO.getEmail());
        this.password = (BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(12)));
        this.phone = (userDTO.getPhone());
        this.des = (userDTO.getDes());
        this.fullName = (userDTO.getFullName());
        this.purpose = (userDTO.getPurpose());
        this.major = (userDTO.getMajor());
        this.scale = (userDTO.getScale());

        this.role = role;
//        this.role = (role);
    }
}
