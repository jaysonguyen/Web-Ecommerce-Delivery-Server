package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.BranchDTO;
import com.example.deliveryecommercebackend.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/branch")
public class BranchController {

    @Autowired
    private BranchService branchServices;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getBranchList() {
        try {
            var listBranch = branchServices.getBranch();
            System.out.println("ok");
            return ResponseEntity.ok().body(listBranch);
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex.getMessage());
            return ResponseEntity.status(400).body("Error from controller: " + ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createBranch(@RequestBody BranchDTO branch) {
        var checkCreate = HttpStatus.OK;
        try {
            checkCreate = branchServices.insertBranch(branch);
            if(checkCreate == HttpStatus.OK)
                return ResponseEntity.ok().body("Insert data success");

            return ResponseEntity.status(checkCreate).body("Insert branch failed");
        } catch (Exception ex) {
            System.out.println("Error from controller");
            return ResponseEntity.status(400).body("Server error");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateBranch(@RequestBody BranchDTO branch) {
        return branchServices.updateBranch(branch);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> deleteBranch(@PathVariable String id) {
        try {
            HttpStatus check = branchServices.deleteBranch(id);
            if (check == HttpStatus.OK) {
                return ResponseEntity.status(check).body("Delete branch success");
            }
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete branch failed");
    }
}
