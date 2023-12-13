package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.DTO.AreaCreatedDTO;
import com.example.deliveryecommercebackend.DTO.AreaDTO;
import com.example.deliveryecommercebackend.model.Area;
import com.example.deliveryecommercebackend.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("{cityId}")
    public ResponseEntity<?> getAreaList(@PathVariable String cityId){
        try {
            var listArea = areaService.getInstance().getAllAreas(cityId);
            return listArea;
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex.getMessage());
            return ResponseEntity.status(400).body("Error from controller: " + ex.getMessage());
        }
    }
    @GetMapping("dropdown/{cityCode}")
    public ResponseEntity<?> getAreaDropdownList(@PathVariable String cityCode){
        try {
            var listArea = areaService.getInstance().getAreasDropdownByCityCode(cityCode);
            return listArea;
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex.getMessage());
            return ResponseEntity.status(400).body("Error from controller: " + ex.getMessage());
        }
    }


//    @GetMapping({"branchCode"})
//    public ResponseEntity<?> getAreaListByBranchCode(@PathVariable String branchCode) {
//        try {
//            var listArea = areaService.getBranchAreas(branchCode);
//            return ResponseEntity.ok().body(listArea);
//        } catch (Exception ex) {
//            System.out.printf("Error from controller" + ex.getMessage());
//            return ResponseEntity.status(400).body("Error from controller: " + ex.getMessage());
//        }
//    }

//    @GetMapping("{id}")
//    public ResponseEntity<?> getAreaById(@PathVariable String id){
//            AreaDTO check = areaService.getAreaById(id);
//        try {
//            AreaDTO area = areaService.getAreaById(id);
//            if (area.getId() == null) {
//                return ResponseEntity.ok().body("Area not found.");
//            } else {
//                return ResponseEntity.ok().body(area);
//            }
//        } catch (Exception ex) {
//            return ResponseEntity.badRequest().body("Error from server");
//        }
//    }

    @PutMapping
    public ResponseEntity<?> updateArea(@RequestBody AreaCreatedDTO area) {
        try {
            HttpStatus check = areaService.getInstance().updateArea(area);
            if(check != HttpStatus.OK)
                return ResponseEntity.status(check).body("Update data failed");
            return ResponseEntity.status(check).body("Update data successfully");
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex);
            return ResponseEntity.badRequest().body("Error fom server" + ex);
        }
    }

    @PostMapping
    public ResponseEntity<?> createArea(@RequestBody AreaCreatedDTO area) {
        try {
            return areaService.getInstance().createAreaAndAssignment(area);
        } catch (Exception ex) {
            System.out.println("Error from controller");
            return ResponseEntity.status(400).body("Server error");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> deleteArea(@PathVariable String id){
        try {
            HttpStatus check = areaService.getInstance().deleteArea(id);
            if (check == HttpStatus.OK) {
                return ResponseEntity.status(check).body("Delete area success");
            }
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete area failed");
    }
}
