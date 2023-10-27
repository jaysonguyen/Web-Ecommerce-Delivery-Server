package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.VoucherDTO;
import com.example.deliveryecommercebackend.model.Voucher;
import com.example.deliveryecommercebackend.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/voucher")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }
    @GetMapping
    @ResponseBody
    public ResponseEntity<?>getAllVoucher() {
        try {
            var listVoucher = voucherService.getAllVouchers();
            if (listVoucher.isEmpty()) {
                return ResponseEntity.ok().body("Empty list voucher.");
            } else {
                return ResponseEntity.ok(listVoucher);
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

//    @GetMapping("{voucher_id}")
//    @ResponseBody
//    public ResponseEntity<Voucher> getVoucherById(@PathVariable String voucher_id) {
//        try {
//            var listVoucher = voucherService.getVoucherById(voucher_id);
//            return ResponseEntity.ok().body(listVoucher);
//        } catch (Exception ex) {
//            return ResponseEntity.badRequest().body(new Voucher());
//        }
//    }

    @PostMapping
    public ResponseEntity<?> createVoucher(@RequestBody VoucherDTO voucher) {
        try {
            HttpStatus checkAdd = voucherService.createVoucher(voucher);
            if(checkAdd == HttpStatus.OK) {
                return ResponseEntity.status(200).body("Insert data successful");
            }
            return ResponseEntity.badRequest().body("Insert success");
        } catch (Exception ex) {
            System.out.println("Error from server, Error:" + ex);
            return ResponseEntity.badRequest().body("Error from voucher");
        }
    }
    @PutMapping("{voucher_id}")
    public ResponseEntity<?> updateVoucher(@PathVariable String voucher_id, @RequestBody VoucherDTO voucher) {
        try {
            HttpStatus checkAdd = voucherService.updateVoucher(voucher_id, voucher);
            if(checkAdd == HttpStatus.OK) {
                return ResponseEntity.status(200).body("Update data successful");
            }
            return ResponseEntity.badRequest().body("Update success");
        } catch (Exception ex) {
            System.out.println("Error from server, Error:" + ex);
            return ResponseEntity.badRequest().body("Error from voucher");
        }
    }
    @DeleteMapping("{voucher_id}")
    public ResponseEntity<?> deleteVoucher(@PathVariable String voucher_id) {
        try {
            HttpStatus checkAdd = voucherService.deleteVoucher(voucher_id);
            if(checkAdd == HttpStatus.OK) {
                return ResponseEntity.status(200).body("Delete data successful");
            }
            return ResponseEntity.badRequest().body("Delete success");
        } catch (Exception ex) {
            System.out.println("Error from server, Error:" + ex);
            return ResponseEntity.badRequest().body("Error from voucher");
        }
    }


}
