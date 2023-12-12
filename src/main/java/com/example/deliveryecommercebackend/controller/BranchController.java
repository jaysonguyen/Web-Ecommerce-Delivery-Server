package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.BranchCreateDTO;
import com.example.deliveryecommercebackend.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
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
            var check = branchServices.getBranchData();
            return (check);
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex.getMessage());
            return ResponseEntity.status(400).body("Error from controller: " + ex.getMessage());
        }
    }
    @GetMapping("/dropdown")
    @ResponseBody
    public ResponseEntity<?> getBranchDropdown(){
        try{
            var check =  branchServices.getBranchDropdown();
            return (check);
        }catch (Exception ex){
            System.out.println("Error from controller" +  ex.getMessage());
            return ResponseEntity.status(400).body("Error from controller" + ex.getMessage());
        }
    }
    @GetMapping("{code}")
    @ResponseBody
    public ResponseEntity<?> getBranchByCode(@PathVariable String code){
        try{
            var check =  branchServices.getBranchModelByCode(code);
            return ResponseEntity.ok().body(check);
        }catch (Exception ex){
            System.out.println("Error from controller" +  ex.getMessage());
            return ResponseEntity.status(400).body("Error from controller" + ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createBranch(@RequestBody BranchCreateDTO branch) {
        try {
            var checkCreate = branchServices.insertBranch(branch);
            return checkCreate;
        } catch (Exception ex) {
            System.out.println("Error from controller");
            return ResponseEntity.status(400).body("Server error");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateBranch(@RequestBody BranchCreateDTO branch) {
        return branchServices.updateBranch(branch);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> deleteBranch(@PathVariable String id) {
        try {
            var check = branchServices.deleteBranch(id);
            return check;
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete branch failed");
    }
}
