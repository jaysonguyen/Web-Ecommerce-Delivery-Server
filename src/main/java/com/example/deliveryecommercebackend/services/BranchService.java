package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.BranchDTO;
import com.example.deliveryecommercebackend.exception.ResourceNotfoundException;
import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.repository.BranchRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepo;

    public HttpStatus createBranch(BranchDTO branchDTO) {

        var checkBranch = branchRepo.findBranchByName(branchDTO.getName());

        if(checkBranch == null) {
            return HttpStatus.BAD_REQUEST;
        }

        Branch branch = new Branch();
        branch.setName(branchDTO.getName());
        branch.setAddress(branchDTO.getAddress());
        branch.setDes(branchDTO.getDes());

        var checkCreate = branchRepo.save(branch);

        if(checkCreate != null) {
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    public List<Branch> getBranch() {
        List<Branch> listBranch = branchRepo.findAll();
        return listBranch;
    }

    public ResponseEntity<?> updateBranch(BranchDTO branchDTO) {
        var checkExistsBranch = branchRepo.findById(branchDTO.getBranch_id()).get();

        if(checkExistsBranch == null) {
            return ResponseEntity.badRequest().body("Exists branch");
        }
        try {
            checkExistsBranch.setName(branchDTO.getName());
            checkExistsBranch.setDes(branchDTO.getDes());

            var checkSave = branchRepo.save(checkExistsBranch);
            if(checkSave != null)
                return ResponseEntity.ok().body("Update branch success");
        } catch (Exception ex) {
            System.out.println("Error from services");
        }
        return ResponseEntity.badRequest().body("Insert branch failed");

    }

    public ResponseEntity<?> deleteBranch(String branchID) {
        var branch = branchRepo.findById(branchID).get();
        if(branch == null) {
            return ResponseEntity.badRequest().body("Exists branch");
        }
        try {
            branch.set_delete(true);
            var checkDelete = branchRepo.save(branch);
            if(checkDelete != null)
                return ResponseEntity.ok().body("Delete branch success");

        } catch (Exception ex) {
            System.out.printf("Error fom services: " + ex);
        }
        return ResponseEntity.badRequest().body("Delete branch failed");
    }

}
