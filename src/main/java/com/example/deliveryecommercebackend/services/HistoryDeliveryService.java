package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.HistoryDeliveryDTO;
import com.example.deliveryecommercebackend.model.HistoryDelivery;
import com.example.deliveryecommercebackend.repository.HistoryDeliveryRepository;
import com.example.deliveryecommercebackend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HistoryDeliveryService {

    private HistoryDeliveryRepository hisRepo;
    private OrderRepository orderRepo;

    public HistoryDeliveryService(HistoryDeliveryRepository hisRepo, OrderRepository orderRepo) {
        this.hisRepo = hisRepo;
        this.orderRepo = orderRepo;
    }

    public boolean confirmRejectPackage(String orderId, String branchId,long moneyCollect, String shipperId, String reasonReject) {
        try {
            var order = orderRepo.findOrderById(orderId);

            if(order != null) {
                order.setAction_code("1");
                orderRepo.save(order);
            }

            //INSERT INTO HISTORY
            var historyDeli = new HistoryDelivery();
            historyDeli.setInput_by(shipperId);
            historyDeli.setData_time(LocalDateTime.now());
            historyDeli.setState("Reject");
            historyDeli.setImage(null);
            historyDeli.setBranch_id(branchId);
            historyDeli.setOrder_id(orderId);
            historyDeli.setReason_reject(reasonReject);
            historyDeli.setMoney_collect(0);
            historyDeli.setShipper_code(shipperId);

            hisRepo.save(historyDeli);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public boolean  confirmReceivePackage(String orderId, String branchId,long moneyCollect, String shipperCode, String state, String reason) {
        try {
            var order = orderRepo.findOrderById(orderId);

            if(order != null) {
                if(reason != null) {
                    order.setAction_code("2");
                } else {
                    order.setAction_code("1");
                }
                orderRepo.save(order);

            }



            //INSERT INTO HISTORY
            var historyDeli = new HistoryDelivery();
            historyDeli.setInput_by(shipperCode);
            historyDeli.setData_time(LocalDateTime.now());
            historyDeli.setState(state);
            historyDeli.setImage(null);
            historyDeli.setBranch_id(branchId);
            historyDeli.setOrder_id(orderId);
            historyDeli.setMoney_collect((long)moneyCollect);
            historyDeli.setShipper_code(shipperCode);
            historyDeli.setReason_reject(reason);

            hisRepo.save(historyDeli);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }


}
