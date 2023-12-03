package com.example.deliveryecommercebackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ShipperAssignmentDTO {
    private String shipping_assigment_id;
    private String user_code;
    private String area_code;
    private boolean status;
    private Date data_date;
    private String branch_code;

}
