package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.BranchCreateDTO;
import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepo;

    public HttpStatus insertBranch(BranchCreateDTO branchCreateDTO) {
        Branch branch = new Branch();

        branch.setName(branchCreateDTO.getName());
        branch.setAddress(branchCreateDTO.getAddress());
        branch.setDes(branchCreateDTO.getDes());

        try {
            Branch checkSave = branchRepo.save(branch);
            if(checkSave != null) {
                return HttpStatus.OK;
            }
        } catch(Exception ex) {
            System.out.printf("Create branch failed - Error" + ex);
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public List<BranchCreateDTO> getBranch() {
        try {
            var branchList = branchRepo.findNoneDeleteBranch();
            List<BranchCreateDTO> res = new ArrayList<BranchCreateDTO>();
            for(Branch branch : branchList){
                BranchCreateDTO temp = new BranchCreateDTO();
                temp.setData(branch);
                res.add(temp);
            }

            return res;
        } catch(Exception ex) {
            System.out.printf("Get user failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public ResponseEntity<?> updateBranch(BranchCreateDTO branchCreateDTO) {
        var checkExistsBranch = branchRepo.findById(branchCreateDTO.getBranch_id()).get();

        if(checkExistsBranch == null) {
            return ResponseEntity.badRequest().body("Exists branch");
        }
        try {
            checkExistsBranch.setName(branchCreateDTO.getName());
            checkExistsBranch.setDes(branchCreateDTO.getDes());

            var checkSave = branchRepo.save(checkExistsBranch);
            if(checkSave != null)
                return ResponseEntity.ok().body("Update branch success");
        } catch (Exception ex) {
            System.out.println("Error from services");
        }
        return ResponseEntity.badRequest().body("Insert branch failed");

    }

    public HttpStatus deleteBranch(String branchID) {
        var branch = branchRepo.findById(branchID).get();
        if(branch == null) {
            return HttpStatus.NOT_FOUND;
        }

        try {
            branch.set_delete(true);
            var checkDelete = branchRepo.save(branch);
            if(checkDelete == null)
                return HttpStatus.CONFLICT;
            return HttpStatus.OK;
        } catch (Exception ex) {
            System.out.printf("Error from service", ex);
            return HttpStatus.BAD_REQUEST;
        }
    }

//    public ResponseEntity<?> getShipperByBranch(String branchID) {
//
//    }
}
