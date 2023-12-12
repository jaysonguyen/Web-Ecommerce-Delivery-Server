package com.example.deliveryecommercebackend.model;


import com.example.deliveryecommercebackend.DTO.order.ProductTypeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    private String name;
    private String des;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date updated;
    @JsonIgnore
    //is deleted
    private boolean state;

    public ProductType(ProductTypeDTO productTypeDTO) {
        this.id = productTypeDTO.getId();
        this.code = productTypeDTO.getCode();
        this.name = productTypeDTO.getName();
        this.des = productTypeDTO.getDes();
        this.created = Date.valueOf(LocalDate.now());
        this.updated = Date.valueOf(LocalDate.now());
        this.state = false;
    }
}
