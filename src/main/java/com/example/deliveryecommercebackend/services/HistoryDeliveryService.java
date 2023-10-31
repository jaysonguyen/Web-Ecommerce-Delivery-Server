//package com.example.deliveryecommercebackend.services;
//
//import com.example.deliveryecommercebackend.DTO.HistoryDeliveryDTO;
//import com.example.deliveryecommercebackend.model.HistoryDelivery;
//import com.example.deliveryecommercebackend.repository.BranchRepository;
//import com.example.deliveryecommercebackend.repository.HistoryDeliveryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class HistoryDeliveryService {
//    @Autowired
//    private HistoryDeliveryRepository hsRepo;
//
//    private BranchRepository branchRepo;
//
//    public ResponseEntity<?> createHistory(HistoryDeliveryDTO hisDTO) {
//
//        try {
//            var branch = branchRepo.findById(hisDTO.getBranch_id()).get();
//            HistoryDelivery his = new HistoryDelivery();
//            his.setState(hisDTO.getState());
//            his.setBranch(branch);
//            his.setData_time(LocalDateTime.now());
//            his.setInput_by(hisDTO.getInput_by());
//
//            var checkSave = branchRepo.save(branch);
//            if(checkSave != null) {
//                return ResponseEntity.ok().body("Insert history success");
//            }
//        }catch (Exception exception) {
//            System.out.println("Error from services" + exception);
//        }
//        return ResponseEntity.badRequest().body("Create history failed");
//    }
//
//    public ResponseEntity<?> getHistory() {
//        try {
//            List<HistoryDelivery> his = hsRepo.findAll();
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
//
////    public ResponseEntity<?> getHistoryByBranch(String branchId) {
////        try {
////            List<HistoryDelivery> his = hsRepo.findHistoryDeliveriesByBranch(branchId);
////            if(his.isEmpty()) {
////                return ResponseEntity.badRequest().body("List empty");
////            }
////            return ResponseEntity.ok().body(his);
////
////
////        }catch (Exception ex) {
////            System.out.println("Error from service");
////        }
////
////        return ResponseEntity.badRequest().body("Get list history failed");
////    }
//}
