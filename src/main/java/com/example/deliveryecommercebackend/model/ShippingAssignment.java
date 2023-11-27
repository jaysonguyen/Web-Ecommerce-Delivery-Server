package com.example.deliveryecommercebackend.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipping_assigment")
public class ShippingAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String shipping_assigment_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "area_id")
    @JsonManagedReference
    private Area area;

    private boolean status;
    private Date data_date;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    @JsonManagedReference
    private Branch branch;

}