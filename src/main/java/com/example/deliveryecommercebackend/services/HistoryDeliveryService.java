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

    public boolean confirmReceivePackage(String orderId, String branchId,long moneyCollect, String shipperCode) {
        try {
            var order = orderRepo.findOrderById(orderId);

            if(order != null) {
                order.setAction_code("1");
                orderRepo.save(order);
            }

            //INSERT INTO HISTORY
            var historyDeli = new HistoryDelivery();
            historyDeli.setInput_by(shipperCode);
            historyDeli.setData_time(LocalDateTime.now());
            historyDeli.setState("Received");
            historyDeli.setImage(null);
            historyDeli.setBranch_id(branchId);
            historyDeli.setOrder_id(orderId);
            historyDeli.setMoney_collect((long)moneyCollect);
            historyDeli.setShipper_code(shipperCode);

            hisRepo.save(historyDeli);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }


}
