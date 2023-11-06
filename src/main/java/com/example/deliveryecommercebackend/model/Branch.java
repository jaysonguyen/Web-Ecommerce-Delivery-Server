package com.example.deliveryecommercebackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Table(name="branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String branch_id;
    private String name;
    private String address;
    private String des;
    @JsonIgnore
    private boolean is_delete;

    @OneToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference
    private City city;

}
