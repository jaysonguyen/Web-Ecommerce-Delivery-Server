package com.example.deliveryecommercebackend.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Shipper extends User {
    private double shipment_salary;
    private int shipmentPoint;
}

