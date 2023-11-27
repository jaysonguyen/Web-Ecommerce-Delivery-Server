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

    public boolean confirmReceivePackage(HistoryDeliveryDTO hisDTO) {
        try {
            var order = orderRepo.findOrderById(hisDTO.getOrder_id());

            if(order != null) {
                order.setAction_code("1");
                orderRepo.save(order);
            }

            //INSERT INTO HISTORY
            var historyDeli = new HistoryDelivery();
            historyDeli.setInput_by(hisDTO.getInput_by());
            historyDeli.setData_time(LocalDateTime.now());
            historyDeli.setState("Received");
            historyDeli.setImage(hisDTO.getImage());
            historyDeli.setBranch_id(hisDTO.getBranch_id());
            historyDeli.setOrder_id(hisDTO.getOrder_id());
            historyDeli.setMoney_collect((long)hisDTO.getMoney_collect());
            historyDeli.setShipper_code(hisDTO.getShipper_code());

            hisRepo.save(historyDeli);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }


}
