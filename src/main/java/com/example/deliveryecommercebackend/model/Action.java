package com.example.deliveryecommercebackend.model;


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
@Table(name="action")
public class Action {
    @Id
    @Column(name = "action_id")
    private int action_id;
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "des")
    private String des;
    @Column(name = "type")
    private String type;
    @Column(name = "created")
    private Date created;
    @Column(name = "is_deleted")
    private boolean is_deleted;
}
