package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.DTO.order.CityDTO;
import com.example.deliveryecommercebackend.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<?> getCityList(){
        try {
            var listCity = cityService.getAllCitys();
            return ResponseEntity.ok().body(listCity);
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex.getMessage());
            return ResponseEntity.status(400).body("Error from controller: " + ex.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCityById(@PathVariable String id){
            CityDTO check = cityService.getCityById(id);
        try {
            CityDTO city = cityService.getCityById(id);
            if (city.getId() == null) {
                return ResponseEntity.ok().body("City not found.");
            } else {
                return ResponseEntity.ok().body(city);
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCity(@RequestBody CityDTO city) {
        try {
            HttpStatus check = cityService.updateCity(city);
            if(check != HttpStatus.OK)
                return ResponseEntity.status(check).body("Update data failed");
            return ResponseEntity.status(check).body("Update data successfully");
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex);
            return ResponseEntity.badRequest().body("Error fom server" + ex);
        }
    }

    @PostMapping
    public ResponseEntity<?> createCity(@RequestBody CityDTO city) {
        var checkCreate = HttpStatus.OK;
        try {
            checkCreate = cityService.insertCity(city);
            if(checkCreate == HttpStatus.OK)
                return ResponseEntity.ok().body("Insert data success");

            return ResponseEntity.status(checkCreate).body("Insert city failed");
        } catch (Exception ex) {
            System.out.println("Error from controller");
            return ResponseEntity.status(400).body("Server error");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> deleteCity(@PathVariable String id){
        try {
            HttpStatus check = cityService.deleteCity(id);
            if (check == HttpStatus.OK) {
                return ResponseEntity.status(check).body("Delete city success");
            }
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete city failed");
    }
}
