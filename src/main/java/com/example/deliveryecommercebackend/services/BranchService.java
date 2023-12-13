package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.BranchCreateDTO;
import com.example.deliveryecommercebackend.DTO.BranchDisplayDTO;
import com.example.deliveryecommercebackend.DTO.BranchDropdownDTO;
import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.City;
import com.example.deliveryecommercebackend.repository.BranchRepository;
import com.example.deliveryecommercebackend.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepo;
    @Autowired
    private CityRepository cityRepo;

    public ResponseEntity<?> insertBranch(BranchCreateDTO branchCreateDTO) {
        //find city
        City city = cityRepo.findNoneDeleteCityByCode(branchCreateDTO.getCity_code());

        if(city.getId() == null) {
            return ResponseEntity.badRequest().body("City not found");
        }

        Branch branch = new Branch();
        branch.setCreate(branchCreateDTO, city);

        try {
            Branch checkSave = branchRepo.save(branch);
            if(checkSave.getBranch_id() != null) {
                return ResponseEntity.ok().body("Insert branch successfully");
            }
        } catch(Exception ex) {
            System.out.printf("Create branch failed - Error" + ex);
        }
        return ResponseEntity.badRequest().body("Something went wrong");
    }

    public ResponseEntity<?> getBranchData() {
        try {
            var branchList = branchRepo.findNoneDeleteBranch();
            List<BranchDisplayDTO> res = new ArrayList<>();
            for(Branch branch : branchList){
                BranchDisplayDTO temp = new BranchDisplayDTO();
                temp.setData(branch);
                res.add(temp);
            }

            return ResponseEntity.ok().body(res);
        } catch(Exception ex) {
            System.out.printf("Get branch failed - Error: " + ex);
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }
    public Branch getBranchModelByCode(String code) {
        try {
            return branchRepo.findBranchByCode(code);
        } catch(Exception ex) {
            System.out.printf("Get branch failed - Error: " + ex);
            return new Branch();
        }
    }
    public  ResponseEntity<?> getBranchDropdown(){
        try{
            var branchDropdown = branchRepo.findAllActiveBranchesDropdown();
            List<BranchDropdownDTO> res = new ArrayList<>();
            for(Branch branch: branchDropdown){
                BranchDropdownDTO temp = new BranchDropdownDTO();
                temp.setData(branch);
                res.add(temp);
            }
            return ResponseEntity.ok().body(res);
        }catch (Exception ex){
            System.out.print("Get branch dropdown failed" + ex);
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }

    public ResponseEntity<?> updateBranch(BranchCreateDTO branchCreateDTO) {
        var checkExistsBranch = branchRepo.findById(branchCreateDTO.getBranch_id()).get();

        if(checkExistsBranch.getBranch_id() == null) {
            return ResponseEntity.badRequest().body("Exists branch");
        }
        try {
            checkExistsBranch.setName(branchCreateDTO.getName());
            checkExistsBranch.setDes(branchCreateDTO.getDes());

            var checkSave = branchRepo.save(checkExistsBranch);
            if(checkSave.getBranch_id() != null) {
                return ResponseEntity.ok().body("Update branch success");
            }
        } catch (Exception ex) {
            System.out.println("Error from services");
        }
        return ResponseEntity.badRequest().body("Insert branch failed");

    }

    public ResponseEntity<?> deleteBranch(String branchID) {
        var branch = branchRepo.findById(branchID).get();
        if(branch.getBranch_id() == null) {
            return ResponseEntity.badRequest().body("Branch not found");
        }

        try {
            branch.set_delete(true);
            var checkDelete = branchRepo.save(branch);
            if(checkDelete.getBranch_id() == null) {
                return ResponseEntity.badRequest().body("Cannot delete");
            }
            return  ResponseEntity.ok().body("Delete branch successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Server went wrong");
        }
    }

//    public ResponseEntity<?> getShipperByBranch(String branchID) {
//
//    }

    public static class Helper {


    }
}
