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
            return ResponseEntity.ok().body(listBranch);
        } catch (Exception ex) {
            System.out.printf("Error from controller", ex);
        }

        return ResponseEntity.status(404).body("Error from controller");
    }

    @PostMapping
    public ResponseEntity<?> createBranchList(@RequestBody BranchDTO branch) {
        try {
            var checkCreate = branchServices.createBranch(branch);
            if(checkCreate == HttpStatus.OK)
                return ResponseEntity.ok().body("Insert data success");
        } catch (Exception ex) {
            System.out.println("Error from controller");
        }
        return ResponseEntity.badRequest().body("Insert branch failed");
    }
}
