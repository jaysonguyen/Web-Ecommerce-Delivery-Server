package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.HistoryDeliveryDTO;
import com.example.deliveryecommercebackend.model.HistoryDelivery;
import com.example.deliveryecommercebackend.model.user.Shipper;
import com.example.deliveryecommercebackend.repository.HistoryDeliveryRepository;
import com.example.deliveryecommercebackend.repository.OrderRepository;
import com.example.deliveryecommercebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Properties;

@Service
public class HistoryDeliveryService {

    private HistoryDeliveryRepository hisRepo;
    private OrderRepository orderRepo;
    private UserRepository userRepo;

    @Autowired
    private JavaMailSender sender;
 

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
                    try {
                        SimpleMailMessage message = new SimpleMailMessage();
                        message.setFrom("01216571415kt@gmail.com");
                        message.setTo(user.getEmail());
                        message.setText("Dear " + user.getFullName() + "\n Thanks for your trusted your delivery has already delivered, money will sent to you at the near time. Thanks for using our services");
                        message.setSubject("[HUFLIT.DELIVERY] Info package");

                        sender.send(message);
                        System.out.println("Send mail successfully");

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    userRepo.save(user);
                }
            }
            orderRepo.save(order);
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
