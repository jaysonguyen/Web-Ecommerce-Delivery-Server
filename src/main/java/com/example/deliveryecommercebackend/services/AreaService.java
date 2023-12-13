package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.AreaCreatedDTO;
import com.example.deliveryecommercebackend.DTO.AreaDTO;
import com.example.deliveryecommercebackend.DTO.AreaDetailDTO;
import com.example.deliveryecommercebackend.Data.AreaSingleton;
import com.example.deliveryecommercebackend.model.Area;
import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.City;
import com.example.deliveryecommercebackend.model.ShippingAssignment;
import com.example.deliveryecommercebackend.repository.AreaRepository;
import com.example.deliveryecommercebackend.repository.BranchRepository;
import com.example.deliveryecommercebackend.repository.CityRepository;
import com.example.deliveryecommercebackend.repository.ShippingAssignmentRepository;
import com.example.deliveryecommercebackend.template.AreaTemplate;
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
public class AreaService extends AreaTemplate {

    @Autowired
    AreaRepository areaRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    private ShippingAssignmentRepository shipRepo;
    @Autowired
    private BranchRepository branchRepository;

    public AreaService() {}
    public static synchronized AreaService getInstance() {
        return AreaSingleton.getInstance();
    }

    public AreaService(AreaRepository areaRepo, CityRepository cityRepo, ShippingAssignmentRepository shipRepo, BranchRepository branchRepo) {
        this.areaRepository = areaRepo;
        this.cityRepository = cityRepo;
        this.shipRepo = shipRepo;
        this.branchRepository = branchRepo;
    }

    public ResponseEntity<?> getAllAreas(String cityId) {
        try {
            //check city exists
            City city = cityRepository.findNoneDeleteCityById(cityId);
            if(city == null) {
                return ResponseEntity.badRequest().body("City not found" );
            }

            List<Area> areas = areaRepository.findNoneDeleteAreaByCity(city);
            List<AreaDetailDTO> res = new ArrayList<>();
            for(Area area : areas){
                AreaDetailDTO temp = new AreaDetailDTO(area);
                res.add(temp);
            }

            return ResponseEntity.ok().body(res);
        } catch(Exception ex) {
            System.out.printf("Get area failed - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }

    public ResponseEntity<?> getAreasDropdownByCityCode(String cityCode) {
        try {
            //check city exists
            City city = cityRepository.findNoneDeleteCityByCode(cityCode);
            if(city == null) {
                return ResponseEntity.badRequest().body("City not found" );
            }

            List<Area> areas = areaRepository.findNoneDeleteAreaByCity(city);
            List<AreaDTO> res = new ArrayList<>();
            for(Area area : areas){
                AreaDTO temp = new AreaDTO(area);
                res.add(temp);
            }

            return ResponseEntity.ok().body(res);
        } catch(Exception ex) {
            System.out.printf("Get area failed - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }

    public List<Area> getBranchAreas(String branchCode) {
        try {
            //check city exists
            City city = cityRepository.findNoneDeleteCityById(branchCode);
            if(city == null) {
                return Collections.emptyList();
            }

            List<Area> areas = areaRepository.findNoneDeleteAreaByCity(city);
//            List<AreaDTO> res = new ArrayList<AreaDTO>();
//            for(Area area : areas){
//                AreaDTO temp = new AreaDTO(area);
//                res.add(temp);
//            }

            return areas;
        } catch(Exception ex) {
            System.out.printf("Get area failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public Area getAreaById(String id){
        try {
            Area area = areaRepository.findNoneDeleteAreaById(id);
            return area;
        } catch(Exception ex) {
            System.out.printf("Get area failed - Error: " + ex);
            return null;
        }
    }
    public HttpStatus updateArea( AreaCreatedDTO area) {
        var checkExistsArea = areaRepository.findByCode(area.getCode());

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
    @Override
    public Area createArea(AreaCreatedDTO area) {
        //check exists city
//        City city = cityRepository.findNoneDeleteCityById(area.getCity());
        City city = cityRepository.findNoneDeleteCityById(area.getCity());
        if(city == null) {
            System.out.println("Create city in create area failed");
            return null;
        }
        Area newArea = new Area();

        newArea.setDataCreate(area, city);

        try {
            Area checkSave = areaRepository.save(newArea);
            if(checkSave != null) {
                return checkSave;
            }
            return null;
        } catch(Exception ex) {
            System.out.printf("Create area failed - Error" + ex.getMessage());
            return null;
        }
    }

    @Override
    protected ShippingAssignment createAssignment(Area area) {
        try{
            Branch branch = branchRepository.findBranchByCity(area.getCity());
            if(branch == null){
                System.out.println("Branch not found in city");
                return null;
            }

            ShippingAssignment newAssign = new ShippingAssignment();
            newAssign.setArea(area);
            newAssign.setData_date(Date.valueOf(LocalDate.now()));
            newAssign.setStatus(true);

            return shipRepo.save(newAssign);
        }catch(Exception exception) {
            System.out.println("Error in create shipping assigment");
            return null;
        }
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
