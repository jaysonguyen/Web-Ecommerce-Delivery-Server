package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.DTO.UserCreateDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

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
    private String code = "USER";
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

    //new
    private double cod; //tien co the rut
    private int point;

//    @JsonIgnore
//    private String role_id_hide;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")

    @ToString.Exclude
    private Role role;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    @ToString.Exclude
    private Branch branch;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Store> stores;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> orders;

    public void setDataCreate(UserCreateDTO userDTO, Role role){
        try {
            this.created = Date.valueOf(LocalDate.now());
            this.updated = Date.valueOf(LocalDate.now());
            this.account = userDTO.getAccount();
            this.email = userDTO.getEmail();
            this.code = role.getName() + new Random().nextInt(1000, 9999);
            this.password = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(12));
            this.phone = userDTO.getPhone();
            this.des = userDTO.getDes();
            this.fullName = userDTO.getFullName();
            this.purpose = userDTO.getPurpose();
            this.major = userDTO.getMajor();
            this.scale = userDTO.getScale();
            this.role = role;
        } catch (Exception ex) {
            // Log the exception for debugging
            System.out.println("Error in setDataCreate: " + ex.getMessage());
        }
    }
}
