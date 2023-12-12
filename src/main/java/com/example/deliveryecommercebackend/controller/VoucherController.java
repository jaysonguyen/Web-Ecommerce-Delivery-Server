package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.VoucherDTO;
import com.example.deliveryecommercebackend.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/voucher")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllVoucher() {
        try {
            var listVoucher = voucherService.getAllVouchers();
            return listVoucher;
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }
    @GetMapping("valid")
    public ResponseEntity<?> getAllValidVoucher() {
        try {
            var listVoucher = voucherService.getValidVouchers();
            return listVoucher;
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

    @GetMapping("{voucher_id}")
    @ResponseBody
    public ResponseEntity<?> getVoucherById(@PathVariable String voucher_id) {
        try {
            var check = voucherService.getVoucherById(voucher_id);
            return check;
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from controller");
        }
    }

    @PostMapping
    public ResponseEntity<?> createVoucher(@RequestBody VoucherDTO voucher) {
        try {
            var checkAdd = voucherService.createVoucher(voucher);
            return checkAdd;
        } catch (Exception ex) {
            System.out.println("Error from controller - Error:" + ex);
            return ResponseEntity.badRequest().body("Error from controller ");
        }
    }

    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadCustomersData(@RequestParam("file") MultipartFile file){
        return this.voucherService.saveVouchersToDatabase(file);
    }

    @PutMapping("")
    public ResponseEntity<?> updateVoucher(@RequestBody VoucherDTO voucherDTO) {
        try {
            var check = voucherService.updateVoucher(voucherDTO);
            return check;
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete user failed");
    }

    @PutMapping("{voucherId}")
    public ResponseEntity<?> deleteVoucher(@PathVariable String voucherId) {
        try {
            var check = voucherService.deleteVoucher(voucherId);
            return check;
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete user failed");
    }

    public ResponseEntity<?> updateStatusVoucher(@RequestBody VoucherDTO voucher) {
        try {
            HttpStatus check = voucherService.updateStatusVoucher(voucher);
            if (check == HttpStatus.OK) {
                return ResponseEntity.status(check).body("Update status voucher success");
            }
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Update status voucher fail");
    }



}
