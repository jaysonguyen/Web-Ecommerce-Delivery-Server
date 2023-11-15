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
            System.out.printf("Get voucher failed - Error: " + ex);
//            return Collections.emptyList();
            return ResponseEntity.badRequest().body("Error from services: " + ex.getMessage());
        }
    }

//    public Voucher getVoucherById(String id) {
//        try {
//            Voucher voucher = voucherRepository.findVoucherById(id);
//            return voucher;
//        } catch(Exception ex) {
//            System.out.printf("Get voucher failed - Error: " + ex);
//            return new Voucher();
//        }
//    }

    public HttpStatus createVoucher(VoucherDTO voucher) {
        Voucher newVoucher = new Voucher();

        newVoucher.setName(voucher.getName());
        newVoucher.setCost(voucher.getCost());
        newVoucher.setStatus(voucher.getStatus());
        newVoucher.setQuantity(voucher.getQuantity());
        newVoucher.setPeriod(voucher.getPeriod());
        newVoucher.setUsed(voucher.getUsed());
        newVoucher.set_deleted(false);
        newVoucher.setCreated(Date.valueOf(LocalDate.now()));

        try {
            voucherRepository.save(newVoucher);
            return HttpStatus.OK;
        } catch(Exception ex) {
            System.out.printf("Create voucher failed - Error" + ex);
            return HttpStatus.BAD_REQUEST;
        }
    }

    public HttpStatus updateVoucher(String code, VoucherDTO voucher) {
        Voucher newVoucher = voucherRepository.findById(code)
                .orElseThrow(() -> new ResourceNotfoundException("Bank not exist with code: " + code));

        newVoucher.setName(voucher.getName());
        newVoucher.setCost(voucher.getCost());
        newVoucher.setStatus(voucher.getStatus());
        newVoucher.setQuantity(voucher.getQuantity());
        newVoucher.setPeriod(voucher.getPeriod());
        newVoucher.setUsed(voucher.getUsed());
        newVoucher.setCreated(Date.valueOf(LocalDate.now()));

        try {
            voucherRepository.save(newVoucher);
            return HttpStatus.OK;
        } catch(Exception ex) {
            System.out.printf("Create voucher failed - Error" + ex);
            return HttpStatus.BAD_REQUEST;
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

    public HttpStatus deleteVoucher(String code) {
        Voucher newVoucher = voucherRepository.findById(code)
                .orElseThrow(() -> new ResourceNotfoundException("Voucher not exist with code: " + code));

        newVoucher.set_deleted(true);

        try {
            voucherRepository.save(newVoucher);
            return HttpStatus.OK;
        } catch(Exception ex) {
            System.out.printf("Create voucher failed - Error" + ex);
            return HttpStatus.BAD_REQUEST;
        }
    }

}
