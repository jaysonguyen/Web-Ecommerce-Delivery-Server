package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.VoucherDTO;
import com.example.deliveryecommercebackend.DTO.VoucherDisplayDTO;
import com.example.deliveryecommercebackend.exception.ResourceNotfoundException;
import com.example.deliveryecommercebackend.model.Voucher;
import com.example.deliveryecommercebackend.repository.VoucherRepository;
import com.example.deliveryecommercebackend.services.utils.ExcelUploadService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public ResponseEntity<?> getAllVouchers() {
        try {
            List<Voucher> vouchers = voucherRepository.findNoneDeleteVoucher();

            List<VoucherDisplayDTO> res = new ArrayList<VoucherDisplayDTO>();
            for(Voucher voucher : vouchers){
                VoucherDisplayDTO temp = new VoucherDisplayDTO();
                temp.setData(voucher);
                res.add(temp);
            }

            return ResponseEntity.ok().body(res);
        } catch(Exception ex) {
            System.out.printf("Get voucher failed - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error from services");
        }
    }
    public ResponseEntity<?> getValidVouchers() {
        try {
            List<Voucher> vouchers = voucherRepository.findNoneDeleteVoucherByStatus("valid");

            List<VoucherDisplayDTO> res = new ArrayList<>();
            for(Voucher voucher : vouchers){
                VoucherDisplayDTO temp = new VoucherDisplayDTO();
                temp.setData(voucher);
                res.add(temp);
            }

            return ResponseEntity.ok().body(res);
        } catch(Exception ex) {
            System.out.printf("Get voucher failed - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error from services");
        }
    }

    public ResponseEntity<?> getVoucherById(String voucherId) {
        try {
            Voucher voucher = voucherRepository.findNoneDeleteVoucherByID(voucherId);
            if(voucher == null) {
                return ResponseEntity.badRequest().body("Voucher not found");
            }

            return ResponseEntity.ok().body(voucher);
        } catch(Exception ex) {
            System.out.printf("Error from service - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error from service");
        }
    }

    public ResponseEntity<?> createVoucher(VoucherDTO voucher) {
        Voucher newVoucher = new Voucher(voucher);
        try {
            voucherRepository.save(newVoucher);
            return ResponseEntity.ok().body("Create new voucher successfully");
        } catch(Exception ex) {
            System.out.printf("Error from service - Error" + ex.getMessage());
            return ResponseEntity.badRequest().body("Error from service");
        }
    }

    public ResponseEntity<?> saveVouchersToDatabase(MultipartFile file){
        if(ExcelUploadService.isValidExcelFile(file)){
            try {
                List<VoucherDTO> vouchers = ExcelUploadService.getVoucherFromExcel(file.getInputStream());

                //check exists
                List<Voucher> vouchersToSave = new ArrayList<>();
                for(var i = 0; i < vouchers.size(); i++){
                    Voucher checkExists = voucherRepository.findNoneDeleteVoucherByCode(vouchers.get(i).getCode());
                    if(checkExists != null) {
                        checkExists.set_deleted(false);
                        this.voucherRepository.save(checkExists);
                    } else {
                        vouchersToSave.add(new Voucher(vouchers.get(i)));
                    }
                }

                if(vouchersToSave.isEmpty()) {
                    return ResponseEntity.ok().body("Save data successfully");
                }

                var check = this.voucherRepository.saveAll(vouchersToSave);
                if(check.isEmpty()) {
                    return ResponseEntity.badRequest().body("Cannot save voucher data");
                }
                return ResponseEntity.ok().body("Save data successfully");
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
        return ResponseEntity.badRequest().body("Error: Illegal file");
    }


    public ResponseEntity<?> updateVoucher(VoucherDTO voucherDTO) {
        try {
            System.out.println(voucherDTO.getVoucherId());
            Voucher newVoucher = voucherRepository.findNoneDeleteVoucherByID(voucherDTO.getVoucherId());
            if(newVoucher == null) {
                return ResponseEntity.badRequest().body("Voucher not found");
            }
            newVoucher = new Voucher(voucherDTO);

            voucherRepository.save(newVoucher);
            return ResponseEntity.ok().body("Update voucher successfully");
        } catch(Exception ex) {
            System.out.printf("Error from service - Error" + ex.getMessage());
            return ResponseEntity.badRequest().body("Error from service");
        }
    }
    public HttpStatus updateStatusVoucher(VoucherDTO voucherDTO) {
        Voucher newVoucher = voucherRepository.findById(voucherDTO.getVoucherId())
                .orElseThrow(() -> new ResourceNotfoundException("Voucher not exist with code: " + voucherDTO.getVoucherId()));
        newVoucher.setStatus(voucherDTO.getStatus());

        try {
            voucherRepository.save(newVoucher);
            return HttpStatus.OK;
        } catch(Exception ex) {
            System.out.printf("Create voucher failed - Error" + ex);
            return HttpStatus.BAD_REQUEST;
        }
    }

    public ResponseEntity<?> deleteVoucher(String id) {
        Voucher newVoucher = voucherRepository.findById(id).get();
        newVoucher.set_deleted(true);
        try {
            voucherRepository.save(newVoucher);
            return ResponseEntity.ok().body("Delete successfully");
        } catch(Exception ex) {
            System.out.printf("Error at service - Error" + ex.getMessage());
            return ResponseEntity.badRequest().body("Error at service");
        }
    }

}
