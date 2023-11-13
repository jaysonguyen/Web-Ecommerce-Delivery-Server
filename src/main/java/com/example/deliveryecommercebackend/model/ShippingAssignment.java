package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.CompositeID.ShippingAssignmentID;
import com.example.deliveryecommercebackend.DTO.ShipperAssignmentDTO;
import com.example.deliveryecommercebackend.model.Area;
import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.Store;
import com.example.deliveryecommercebackend.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipping_assigment")
@IdClass(ShippingAssignmentID.class)

public class ShippingAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String shipping_assigment_id;

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "area_id")
    @JsonManagedReference
    private Area area;

    @Id
    private boolean status;
    private Date data_date;

    @Id
    @ManyToOne
    @JoinColumn(name = "branch_id")
    @JsonManagedReference
    private Branch branch;

}
