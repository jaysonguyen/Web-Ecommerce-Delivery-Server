package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Area;
import com.example.deliveryecommercebackend.model.City;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {
    private String id;
    private String code;
    private String name;
    private String type;
    private Date created;
    private Date updated;
    private String des;
    public CityDTO(City city) {
        this.id = city.getId();
        this.code = city.getCode();
        this.name = city.getName();
        this.type = city.getType();
        this.created = city.getCreated();
        this.updated = city.getUpdated();
        this.des = city.getDes();
    }
}
