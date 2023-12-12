package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.model.HistoryOrder;
import com.example.deliveryecommercebackend.repository.BranchRepository;
import com.example.deliveryecommercebackend.repository.HistoryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryOrderService {
    @Autowired
    private HistoryOrderRepository hsRepo;
    @Autowired
    private BranchRepository branchRepo;
    public ResponseEntity<?> getHistoryByOrder(String orderId) {
        try {
            List<HistoryOrder> history = hsRepo.findHistoryByOrder(orderId);
            return ResponseEntity.ok().body(history);
        }catch (Exception ex) {
            System.out.println("Error from service - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }

//    public ResponseEntity<?> getHistoryByBranch(String branchId) {
//        try {
//            List<HistoryOrder> his = hsRepo.findHistoryDeliveriesByBranch(branchId);
//            if(his.isEmpty()) {
//                return ResponseEntity.badRequest().body("List empty");
//            }
//            return ResponseEntity.ok().body(his);
//
//
//        }catch (Exception ex) {
//            System.out.println("Error from service");
//        }
//
//        return ResponseEntity.badRequest().body("Get list history failed");
//    }
}
