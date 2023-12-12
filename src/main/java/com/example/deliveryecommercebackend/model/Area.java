package com.example.deliveryecommercebackend.model;

import com.example.deliveryecommercebackend.DTO.AreaCreatedDTO;
import com.example.deliveryecommercebackend.model.ShippingAssignment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(name = "name")
    private String name;
    @Column(updatable = false)
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date updated;
    @Column(name = "des")
    private String des;
    @JsonIgnore
    private boolean is_delete;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    @ToString.Exclude
    @JsonBackReference
    private City city;


    @OneToOne(mappedBy = "area")
    @JsonBackReference
    private ShippingAssignment shippingAssignment;

    public void setDataCreate(AreaCreatedDTO areaCreatedDTO, City city) {
        this.code = areaCreatedDTO.getCode();
        this.name = areaCreatedDTO.getName();
        this.created = Date.valueOf(LocalDate.now());
        this.updated = Date.valueOf(LocalDate.now());
        this.des = areaCreatedDTO.getDes();
        this.is_delete = false;
        this.city = city;
    }
}