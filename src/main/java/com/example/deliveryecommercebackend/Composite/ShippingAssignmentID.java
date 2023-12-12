package com.example.deliveryecommercebackend.Composite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ShippingAssignmentID implements Serializable {

    private String shipping_assigment_id;
    private String user_id;
    private boolean status;
    private String branch_id;
}
