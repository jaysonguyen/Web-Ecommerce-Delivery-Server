package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.CityDropdownDTO;
import com.example.deliveryecommercebackend.DTO.order.CityDTO;
import com.example.deliveryecommercebackend.model.City;
import com.example.deliveryecommercebackend.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CityService {


    @Autowired
    CityRepository cityRepository;

    public ResponseEntity<?> getAllCitys() {
        try {
            List<City> list = cityRepository.findNoneDeleteCity();

            List<CityDTO> cityDTOS = new ArrayList<>();
            for(var item : list){
                CityDTO temp = new CityDTO(item);
                cityDTOS.add(temp);
            }

            return ResponseEntity.ok().body(cityDTOS);
        } catch(Exception ex) {
            System.out.printf("Get city failed - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error");
        }
    }

    public ResponseEntity<?> getCityListDropDown() {
        try {
            //check orders exists
            List<City> cityList = cityRepository.findNoneDeleteCity();
            List<CityDropdownDTO> cityDTOS = new ArrayList<>();
            for(City city : cityList){
                CityDropdownDTO temp = new CityDropdownDTO(city);
                cityDTOS.add(temp);
            }

            return ResponseEntity.ok().body(cityDTOS);
        } catch(Exception ex) {
            System.out.printf("Get city list failed - Error: " + ex);
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }

    public City getCityDetailsById(String id){
        try {
            return cityRepository.findNoneDeleteCityById(id);
        } catch(Exception ex) {
            System.out.printf("Get city failed - Error: " + ex);
            return new City();
        }
    }

    public ResponseEntity<?> getCityById(String id){
        try {
            City city = cityRepository.findNoneDeleteCityById(id);
            return ResponseEntity.ok().body(new CityDTO(city));
        } catch(Exception ex) {
            System.out.printf("Get city failed - Error: " + ex);
            return ResponseEntity.badRequest().body(new CityDTO());
        }
    }
    public HttpStatus updateCity( CityDTO city) {
        City checkExistsCity = cityRepository.findById(city.getId()).get();

        if(checkExistsCity.getId() == null) {
            return HttpStatus.CONFLICT;
        }
        try {
            checkExistsCity.setName(city.getName());
            checkExistsCity.setDes(city.getDes());
            checkExistsCity.setUpdated(Date.valueOf(LocalDate.now()));

            var checkSave = cityRepository.save(checkExistsCity);
            if(checkSave.getId() != null)
                return HttpStatus.OK;
            return HttpStatus.CONFLICT;
        } catch (Exception ex) {
            System.out.println("Error from services");
            return HttpStatus.BAD_REQUEST;
        }
    }
    public HttpStatus createCity(CityDTO city) {
        City newCity = new City();

        newCity.setCode(city.getCode());
        newCity.setName(city.getName());
        newCity.setDes(city.getDes());
        newCity.set_delete(false);
        newCity.setUpdated(Date.valueOf(LocalDate.now()));
        newCity.setCreated(Date.valueOf(LocalDate.now()));


        try {
            City checkSave = cityRepository.save(newCity);
            if(checkSave != null) {
                return HttpStatus.OK;
            }
            return HttpStatus.BAD_REQUEST;
        } catch(Exception ex) {
            System.out.printf("Create city failed - Error" + ex);
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    public HttpStatus insertCity(CityDTO cityDTO) {
        City newCity = new City();

        newCity.setCode(cityDTO.getCode());
        newCity.setName(cityDTO.getName());
        newCity.setDes(cityDTO.getDes());
        newCity.setUpdated(Date.valueOf(LocalDate.now()));
        newCity.setCreated(Date.valueOf(LocalDate.now()));


        try {
            City checkSave = cityRepository.save(newCity);
            if(checkSave != null) {
                return HttpStatus.OK;
            }
        } catch(Exception ex) {
            System.out.printf("Create city failed - Error" + ex);
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public HttpStatus deleteCity(String id){
        City city = cityRepository.findNoneDeleteCityById(id);

        city.set_delete(true);

        try {
            var checkUpdate = cityRepository.save(city);
            if(checkUpdate == null) {
                return HttpStatus.CONFLICT;
            }
            return HttpStatus.OK;
        } catch (Exception ex) {
            System.out.printf("Error from service", ex);
            return HttpStatus.BAD_REQUEST;
        }
    }
}
