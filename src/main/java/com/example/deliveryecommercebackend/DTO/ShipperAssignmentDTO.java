package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Area;
import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.model.ShippingAssignment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ShipperAssignmentDTO {
    private String shipping_assigment_id;
<<<<<<< HEAD
    private String user_code;
    private String area_code;
    private boolean status;
    private Date data_date;
    private String branch_code;
=======
    private String user_id;
    private String area_id;
    private boolean status;
    private Date data_date;
    private String branch_id;
>>>>>>> 52b639102fe01d07370680cd43178b2757f86186

}
