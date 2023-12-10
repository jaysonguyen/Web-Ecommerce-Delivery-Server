package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.ProductTypeDropdownDTO;
import com.example.deliveryecommercebackend.DTO.order.ProductTypeDTO;
import com.example.deliveryecommercebackend.model.ProductType;
import com.example.deliveryecommercebackend.repository.ProductTypeRepository;
import com.example.deliveryecommercebackend.services.utils.ExcelUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public ResponseEntity<?> saveProductypesToDatabase(MultipartFile file){
        if(ExcelUploadService.isValidExcelFile(file)){
            try {
                List<ProductTypeDTO> productTypes = ExcelUploadService.getProductTypeFromExcel(file.getInputStream());

                //check exists
                List<ProductType> productTypesToSave = new ArrayList<>();
                for(var i = 0; i < productTypes.size(); i++){
                    ProductType checkExists = productTypeRepository.findDeleteProductTypeByCode(productTypes.get(i).getCode());
                    if(checkExists != null) {
                        checkExists.setState(false);
                        checkExists.setUpdated(Date.valueOf(LocalDate.now()));
                        this.productTypeRepository.save(checkExists);
                    } else {
                        productTypesToSave.add(new ProductType(productTypes.get(i)));
                    }
                }

                if(productTypesToSave.isEmpty()) {
                    return ResponseEntity.ok().body("Save data successfully");
                }

                var check = this.productTypeRepository.saveAll(productTypesToSave);
                if(check.isEmpty()) {
                    return ResponseEntity.badRequest().body("Cannot save product type data");
                }
                return ResponseEntity.ok().body("Save data successfully");
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
        return ResponseEntity.badRequest().body("Error: Illegal file");
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
        ProductType newProductType = new ProductType(productType);

        try {
            //check if exists
            ProductType checkExists = productTypeRepository.findNoneDeleteProductTypeByCode(productType.getCode());
            if(checkExists != null){
                checkExists.setState(false);
                return HttpStatus.OK;
            }

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
