package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.AreaDTO;
import com.example.deliveryecommercebackend.model.Area;
import com.example.deliveryecommercebackend.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class AreaService {


    @Autowired
    AreaRepository areaRepository;

    public List<Area> getAllAreas() {
        try {
            return areaRepository.findNoneDeleteArea();
        } catch(Exception ex) {
            System.out.printf("Get area failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public AreaDTO getAreaById(String id){
        try {
            Area area = areaRepository.findNoneDeleteAreaById(id);
            return new AreaDTO(area);
        } catch(Exception ex) {
            System.out.printf("Get area failed - Error: " + ex);
            return new AreaDTO();
        }
    }
    public HttpStatus updateArea( AreaDTO area) {
        var checkExistsArea = areaRepository.findById(area.getId()).get();

        if(checkExistsArea == null) {
            return HttpStatus.CONFLICT;
        }
        try {
            checkExistsArea.setName(area.getName());
            checkExistsArea.setDes(area.getDes());
            checkExistsArea.setUpdated(Date.valueOf(LocalDate.now()));

            var checkSave = areaRepository.save(checkExistsArea);
            if(checkSave != null)
                return HttpStatus.OK;
            return HttpStatus.CONFLICT;
        } catch (Exception ex) {
            System.out.println("Error from services");
            return HttpStatus.BAD_REQUEST;
        }
    }
    public HttpStatus createArea(AreaDTO area) {
        Area newArea = new Area();

        newArea.setCode(area.getCode());
        newArea.setName(area.getName());
        newArea.setDes(area.getDes());
        newArea.set_delete(false);
        newArea.setUpdated(Date.valueOf(LocalDate.now()));
        newArea.setCreated(Date.valueOf(LocalDate.now()));


        try {
            Area checkSave = areaRepository.save(newArea);
            if(checkSave != null) {
                return HttpStatus.OK;
            }
        } catch(Exception ex) {
            System.out.printf("Create area failed - Error" + ex);
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public HttpStatus deleteArea(String id){
        Area area = areaRepository.findNoneDeleteAreaById(id);
        area.set_delete(true);
        try {
            var checkUpdate = areaRepository.save(area);
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
