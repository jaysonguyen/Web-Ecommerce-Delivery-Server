package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.model.HistoryVoucher;
import com.example.deliveryecommercebackend.model.Order;
import com.example.deliveryecommercebackend.model.Voucher;
import com.example.deliveryecommercebackend.model.user.User;
import com.example.deliveryecommercebackend.repository.BranchRepository;
import com.example.deliveryecommercebackend.repository.HistoryVoucherRepository;
import com.example.deliveryecommercebackend.repository.OrderRepository;
import com.example.deliveryecommercebackend.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryVoucherService {
    @Autowired
    private HistoryVoucherRepository hsRepo;
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private VoucherRepository voucherRepo;
    public ResponseEntity<?> getHistoryByVoucher(String voucherId) {
        try {
            List<HistoryVoucher> history = hsRepo.findHistoryByVoucher(voucherId);

            return ResponseEntity.ok().body(history);
        }catch (Exception ex) {
            System.out.println("Error from service - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }
    public ResponseEntity<?> getHistoryByOrder(String orderId) {
        try {
            List<HistoryVoucher> history = hsRepo.findHistoryByOrder(orderId);

            return ResponseEntity.ok().body(history);
        }catch (Exception ex) {
            System.out.println("Error from service - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }
    public boolean createHistoryByVoucher(String voucherId, String orderId, User user) {
        try {

            HistoryVoucher historyVoucher = new HistoryVoucher(voucherId, orderId, user);

            var check = hsRepo.save(historyVoucher);
            if(check.getHistory_id() == null) {
                System.out.println("Create voucher history failed!");
                return false;
            }
            System.out.println("Create voucher history successfully!");
            return true;
        }catch (Exception ex) {
            System.out.println("Error from service - Error: " + ex.getMessage());
            return false;
        }
    }

//    public ResponseEntity<?> getHistoryByBranch(String branchId) {
//        try {
//            List<HistoryVoucher
//           > his = hsRepo.findHistoryDeliveriesByBranch(branchId);
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
