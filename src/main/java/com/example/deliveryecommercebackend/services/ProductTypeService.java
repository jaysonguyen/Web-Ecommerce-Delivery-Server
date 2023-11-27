package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.ProductTypeDropdownDTO;
import com.example.deliveryecommercebackend.DTO.order.ProductTypeDTO;
import com.example.deliveryecommercebackend.model.ProductType;
import com.example.deliveryecommercebackend.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductTypeService {


    @Autowired
    ProductTypeRepository productTypeRepository;

    public List<ProductType> getAllProductTypes() {
        try {
            return productTypeRepository.findNoneDeleteProductType();
        } catch(Exception ex) {
            System.out.printf("Get productType failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public ResponseEntity<?> getProductTypeDropdown() {
        try {
            List<ProductType> prot = productTypeRepository.findNoneDeleteProductType();
            List<ProductTypeDropdownDTO> protDTO = new ArrayList<>();
            for(var item : prot){
                ProductTypeDropdownDTO temp = new ProductTypeDropdownDTO(item);
                protDTO.add(temp);
            }

            return ResponseEntity.ok().body(protDTO);
        } catch(Exception ex) {
            System.out.printf("Get productType failed - Error: " + ex);
            return ResponseEntity.badRequest().body("Error from service");
        }
    }

    public ProductTypeDTO getProductTypeById(String id){
        try {
            ProductType productType = productTypeRepository.findNoneDeleteProductTypeById(id);
            return new ProductTypeDTO(productType);
        } catch(Exception ex) {
            System.out.printf("Get productType failed - Error: " + ex);
            return new ProductTypeDTO();
        }
    }
    public HttpStatus updateProductType( ProductTypeDTO productType) {
        var checkExistsProductType = productTypeRepository.findById(productType.getId()).get();

        if(checkExistsProductType == null) {
            return HttpStatus.CONFLICT;
        }
        try {
            checkExistsProductType.setName(productType.getName());
            checkExistsProductType.setDes(productType.getDes());
            checkExistsProductType.setUpdated(Date.valueOf(LocalDate.now()));

            var checkSave = productTypeRepository.save(checkExistsProductType);
            if(checkSave != null)
                return HttpStatus.OK;
            return HttpStatus.CONFLICT;
        } catch (Exception ex) {
            System.out.println("Error from services");
            return HttpStatus.BAD_REQUEST;
        }
    }
    public HttpStatus createProductType(ProductTypeDTO productType) {
        ProductType newProductType = new ProductType();

        newProductType.setName(productType.getName());
        newProductType.setDes(productType.getDes());
        newProductType.setState(false);
        newProductType.setUpdated(Date.valueOf(LocalDate.now()));
        newProductType.setCreated(Date.valueOf(LocalDate.now()));


        try {
            ProductType checkSave = productTypeRepository.save(newProductType);
            if(checkSave != null) {
                return HttpStatus.OK;
            }
        } catch(Exception ex) {
            System.out.printf("Create productType failed - Error" + ex);
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public HttpStatus deleteProductType(String id){
        ProductType productType = productTypeRepository.findNoneDeleteProductTypeById(id);
        productType.setState(true);
        try {
            var checkUpdate = productTypeRepository.save(productType);
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
