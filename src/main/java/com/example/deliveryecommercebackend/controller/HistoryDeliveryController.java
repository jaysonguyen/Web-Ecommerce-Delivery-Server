//package com.example.deliveryecommercebackend.controller;
//
//import com.example.deliveryecommercebackend.services.HistoryDeliveryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin("*")
//@RequestMapping("/api/history/delivery")
//@RestController
//public class HistoryDeliveryController {
//
//
//    @Autowired
//
//    private HistoryDeliveryService hisServices;
//
//    @GetMapping
//    @ResponseBody
//    public ResponseEntity<?> getAllHistory() {
//        return hisServices.getHistory();
//    }
//
////
////    @GetMapping("{branch_id}")
////    @ResponseBody
////    public ResponseEntity<?> getListHistoryDeliveryByBranchId(@PathVariable String branchId) {
////        return hisServices.getHistoryByBranch(branchId);
////    }
//
//
//
//
//}
