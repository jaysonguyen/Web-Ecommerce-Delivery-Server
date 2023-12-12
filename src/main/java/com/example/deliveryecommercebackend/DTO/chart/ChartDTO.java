package com.example.deliveryecommercebackend.DTO.chart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChartDTO {
    ArrayList<String> labels;
    ArrayList<DatasetDTO> points;
}
