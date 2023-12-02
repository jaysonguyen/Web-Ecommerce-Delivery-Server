package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.VoucherDTO;
import com.example.deliveryecommercebackend.DTO.VoucherDisplayDTO;
import com.example.deliveryecommercebackend.exception.ResourceNotfoundException;
import com.example.deliveryecommercebackend.model.Voucher;
import com.example.deliveryecommercebackend.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
