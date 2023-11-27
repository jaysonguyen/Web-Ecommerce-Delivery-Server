package com.example.deliveryecommercebackend.DTO.chart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataQuery {
    long counts;
    Date label;
    String group;
}
