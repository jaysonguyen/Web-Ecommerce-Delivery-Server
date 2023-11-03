package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.OrderDisplayListDTO;
import com.example.deliveryecommercebackend.model.*;
import com.example.deliveryecommercebackend.model.Order;
import com.example.deliveryecommercebackend.repository.ActionRepository;
import com.example.deliveryecommercebackend.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private ActionRepository actionRepo;

    public List<OrderDisplayListDTO> getAllOrderByAction(String orderId, String actionCode) {
        try {
            // find action match
            Action action = actionRepo.findActionByCode(actionCode);
            if(action == null) {
                return Collections.emptyList();
            }

            //check orders exists
            List<Order> orders = orderRepo.findOrderByAction(actionCode);
            if(orders == null) {
                return Collections.emptyList();
            }

            List<OrderDisplayListDTO> res = new ArrayList<OrderDisplayListDTO>();
            for(Order order : orders){
                OrderDisplayListDTO temp = new OrderDisplayListDTO(order, action);
                res.add(temp);
            }

            return res;
        } catch(Exception ex) {
            System.out.printf("Get order list failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

//    public HttpStatus createOrder(OrderDTO orderDto) {
//        var order = orderRepo.findById(orderDto.getOrder()).get();
//        Order order = new Order();
//
//
//        try {
//            var checkSave = orderRepo.save(order);
//            if(checkSave != null) {
//                return HttpStatus.OK;
//            }
//        } catch (Exception ex) {
//            System.out.printf("Error from service: " + ex);
//        }
//
//        return HttpStatus.BAD_REQUEST;
//    }

//    public HttpStatus updateOrder(OrderDTO orderDto) {
//        var getOrder = orderRepo.findById(orderDto.getOrder_id()).get();
//
//        return HttpStatus.OK;
//    }
}
