package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.HistoryDeliveryDTO;
import com.example.deliveryecommercebackend.model.HistoryDelivery;
import com.example.deliveryecommercebackend.model.user.Shipper;
import com.example.deliveryecommercebackend.repository.HistoryDeliveryRepository;
import com.example.deliveryecommercebackend.repository.OrderRepository;
import com.example.deliveryecommercebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HistoryDeliveryService {

    private HistoryDeliveryRepository hisRepo;
    private OrderRepository orderRepo;
    private UserRepository userRepo;

    public HistoryDeliveryService(HistoryDeliveryRepository hisRepo, OrderRepository orderRepo, UserRepository userRepo) {
        this.hisRepo = hisRepo;
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    public boolean  confirmReceivePackage(String orderId, String branchId,long moneyCollect, String shipperID, String state, String reason, String image) {
        try {
            var order = orderRepo.findOrderById(orderId);
            Shipper user = userRepo.findShipperById(shipperID);

            if(order != null) {
                if(state.toLowerCase().equals("reject")) {
                    order.setAction_code("5");
                } else {
                    order.setAction_code("7");
                    if(order.getShip_cost() >= 0)
                    {
                        double salary = user.getShipment_salary() + order.getShip_cost();
                        int point = user.getPoint() + 1;
                        user.setShipment_salary(salary);
                        user.setPoint(point);
                    }
                    userRepo.save(user);
                }
            }
            orderRepo.save(order);
            System.out.println(image);

            //INSERT INTO HISTORY
            var historyDeli = new HistoryDelivery();
            historyDeli.setInput_by(shipperID);
            historyDeli.setData_time(LocalDateTime.now());
            historyDeli.setState(state);
            historyDeli.setImage(image);
            historyDeli.setBranch_id(branchId);
            historyDeli.setOrder_id(orderId);
            historyDeli.setMoney_collect(moneyCollect);
            historyDeli.setShipper_code(shipperID);
            historyDeli.setReason_reject(reason);

            hisRepo.save(historyDeli);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }


}
