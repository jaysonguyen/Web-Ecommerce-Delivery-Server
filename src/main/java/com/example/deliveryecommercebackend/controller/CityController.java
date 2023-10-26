package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.model.City;
import com.example.deliveryecommercebackend.model.City;
import com.example.deliveryecommercebackend.services.CityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> getCityList(){
        return cityService.getAllCitys();
    }

    @GetMapping("{code}")
    public ResponseEntity<City> getCityById(@PathVariable String code){
        return cityService.getCityById(code);
    }

    @PutMapping("{code}")
    public ResponseEntity<City> updateCity(@PathVariable String code,@RequestBody City bankDetails) {
        return cityService.updateCity(code, bankDetails);
    }

    @PostMapping
    public City createCity(@RequestBody City bankDetails) {
        return cityService.createCity(bankDetails);
    }

    @DeleteMapping("{code}")
    public ResponseEntity<HttpStatus> deleteCity(@PathVariable String code){
        return cityService.deleteCity(code);
    }
}
