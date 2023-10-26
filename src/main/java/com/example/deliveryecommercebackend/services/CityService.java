package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.exception.ResourceNotfoundException;
import com.example.deliveryecommercebackend.model.City;
import com.example.deliveryecommercebackend.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class CityService {


    @Autowired
    CityRepository cityRepository;

    public List<City> getAllCitys() {
        try {
            return cityRepository.findAll();
        } catch(Exception ex) {
            System.out.printf("Get city failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public ResponseEntity<City> getCityById(String code){
        City city = cityRepository.findById(code)
                .orElseThrow(() -> new ResourceNotfoundException("City not exist with code:" + code));
        return ResponseEntity.ok(city);
    }
    public ResponseEntity<City> updateCity( String code, City cityDetails) {
        City updateCity = cityRepository.findById(code)
                .orElseThrow(() -> new ResourceNotfoundException("City not exist with id: " + code));

        updateCity.setCode(cityDetails.getCode());
        updateCity.setName(cityDetails.getName());
        updateCity.setDes(cityDetails.getDes());

        cityRepository.save(updateCity);

        return ResponseEntity.ok(updateCity);
    }
    public City createCity(City cityDetails) {
        return cityRepository.save(cityDetails);
    }

    public ResponseEntity<HttpStatus> deleteCity(String code){

        City city = cityRepository.findById(code)
                .orElseThrow(() -> new ResourceNotfoundException("City not exist with code: " + code));

        cityRepository.delete(city);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
