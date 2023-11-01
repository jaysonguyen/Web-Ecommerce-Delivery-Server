package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.DTO.ProductTypeDTO;
import com.example.deliveryecommercebackend.services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<?> getProductTypeList(){
        try {
            var listProductType = productTypeService.getAllProductTypes();
            return ResponseEntity.ok().body(listProductType);
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex.getMessage());
            return ResponseEntity.status(400).body("Error from controller: " + ex.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductTypeById(@PathVariable String id){
            ProductTypeDTO check = productTypeService.getProductTypeById(id);
        try {
            ProductTypeDTO productType = productTypeService.getProductTypeById(id);
            if (productType.getId() == null) {
                return ResponseEntity.ok().body("ProductType not found.");
            } else {
                return ResponseEntity.ok().body(productType);
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProductType(@RequestBody ProductTypeDTO productType) {
        try {
            HttpStatus check = productTypeService.updateProductType(productType);
            if(check != HttpStatus.OK)
                return ResponseEntity.status(check).body("Update data failed");
            return ResponseEntity.status(check).body("Update data successfully");
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex);
            return ResponseEntity.badRequest().body("Error fom server" + ex);
        }
    }

    @PostMapping
    public ResponseEntity<?> createProductType(@RequestBody ProductTypeDTO productType) {
        var checkCreate = HttpStatus.OK;
        try {
            checkCreate = productTypeService.createProductType(productType);
            if(checkCreate == HttpStatus.OK)
                return ResponseEntity.ok().body("Insert data success");

            return ResponseEntity.status(checkCreate).body("Insert productType failed");
        } catch (Exception ex) {
            System.out.println("Error from controller");
            return ResponseEntity.status(400).body("Server error");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> deleteProductType(@PathVariable String id){
        try {
            HttpStatus check = productTypeService.deleteProductType(id);
            if (check == HttpStatus.OK) {
                return ResponseEntity.status(check).body("Delete productType success");
            }
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete productType failed");
    }
}
