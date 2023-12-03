package com.example.deliveryecommercebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(updatable = false)
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date updated;
    @Column(name = "des")
    private String des;
    @JsonIgnore
    private boolean is_delete;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonManagedReference
//    @JsonBackReference
    private List<Area> areas;

    @OneToOne(mappedBy = "city", cascade = CascadeType.ALL)
//    @JsonManagedReference
    @JsonBackReference
    private Branch branch;

}