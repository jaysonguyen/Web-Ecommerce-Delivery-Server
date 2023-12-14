package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.VoucherDTO;
import com.example.deliveryecommercebackend.model.Voucher;
import com.example.deliveryecommercebackend.services.VoucherService;
import com.example.deliveryecommercebackend.services.VoucherService_v2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/voucher_v2")
public class VoucherV2Controller {

    @Autowired
    private VoucherService_v2 voucherService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllVoucher() {
        try {
            var listVoucher = voucherService.getAll();
            return ResponseEntity.ok().body(listVoucher);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

    @GetMapping("{voucher_id}")
    @ResponseBody
    public ResponseEntity<?> getVoucherById(@PathVariable String voucher_id) {
        try {
            var check = voucherService.getByID(voucher_id);
            return ResponseEntity.ok().body(check);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from controller");
        }
    }

    @PostMapping("duplicate/{id}")
    public ResponseEntity<?> duplicateVoucher(@PathVariable String id) {
        try {
            var check = voucherService.duplicate(id);
            return ResponseEntity.ok().body(check);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from controller");
        }
    }

    @PostMapping
    public ResponseEntity<?> createVoucher(@RequestBody Voucher voucher) {
        try {
            return voucherService.performCURD(voucher, "create");
        } catch (Exception ex) {
            System.out.println("Error from controller - Error:" + ex);
            return ResponseEntity.badRequest().body("Error from controller ");
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateVoucher(@RequestBody Voucher voucher) {
        try {
            return voucherService.performCURD(voucher, "update");
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete user failed");
    }

    @PutMapping("{voucherId}")
    public ResponseEntity<?> deleteVoucher(@PathVariable String voucherId) {
        try {
            Voucher voucher = voucherService.getByID(voucherId);
            if(voucher == null) return ResponseEntity.badRequest().body("Voucher not found");
            return voucherService.performCURD(voucher, "update");
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete user failed");
    }
}
