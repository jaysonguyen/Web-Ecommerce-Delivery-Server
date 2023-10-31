package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.VoucherDTO;
import com.example.deliveryecommercebackend.model.Voucher;
import com.example.deliveryecommercebackend.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("{id}")
    public ResponseEntity<?> updateVoucher(@PathVariable String id) {
        try {
            HttpStatus check = voucherService.deleteVoucher(id);
            if (check == HttpStatus.OK) {
                return ResponseEntity.status(check).body("Delete voucher success");
            }
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete user failed");
    }


}
