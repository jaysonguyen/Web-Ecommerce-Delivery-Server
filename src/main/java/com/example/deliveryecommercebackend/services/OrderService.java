package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.OrderDTO;
import com.example.deliveryecommercebackend.model.Order;
import com.example.deliveryecommercebackend.repository.OrderRepository;
import com.example.deliveryecommercebackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class OrderService {

    @Autowired

    private UserRepository userRepo;
    private OrderRepository orderRepo;

    public HttpStatus createOrder(OrderDTO orderDto) {
        var user = userRepo.findById(orderDto.getUser()).get();
        Order order = new Order();
        order.setOrder_action(orderDto.getOrder_action());
        order.setOrder_city(orderDto.getOrder_city());
        order.setShip_cost(orderDto.getShip_cost());
        order.setReceiver(orderDto.getReceiver());
        order.setProduct_type(orderDto.getProduct_type());
        order.setCreated(LocalDateTime.now());
        order.setUpdated(LocalDateTime.now());
        order.setAddress(orderDto.getAddress());
        order.setCollect_money(orderDto.isCollect_money());
        order.setCost(orderDto.getCost());
        order.setProduct(orderDto.getProduct());
        order.setPackage_order(orderDto.getPackage_order());
        order.setTotal_cost(orderDto.getTotal_cost());
        order.setUser(user);
        order.setShipper(order.getShipper());

        try {
            var checkSave = orderRepo.save(order);
            if(checkSave != null) {
                return HttpStatus.OK;
            }
        } catch (Exception ex) {
            System.out.printf("Error from service: " + ex);
        }

        return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus updateOrder(OrderDTO orderDto) {
        var getOrder = orderRepo.findById(orderDto.getOrder_id()).get();

        return HttpStatus.OK;
    }
}
