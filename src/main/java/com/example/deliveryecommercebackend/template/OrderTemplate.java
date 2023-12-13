package com.example.deliveryecommercebackend.template;

import com.example.deliveryecommercebackend.DTO.AreaDTO;
import com.example.deliveryecommercebackend.DTO.OrderCreateDTO;
import com.example.deliveryecommercebackend.DTO.ShipperOrderDTO;
import com.example.deliveryecommercebackend.DTO.order.GetOrderListParams;
import com.example.deliveryecommercebackend.DTO.order.NoteDTO;
import com.example.deliveryecommercebackend.model.user.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class OrderTemplate {
    protected abstract ResponseEntity<?> getAllOrderByAction(String actionCode, String userID, GetOrderListParams params);
    protected abstract ResponseEntity<?> getOrderByCode(String orderCode);
    protected abstract ResponseEntity<?> getOrderByCustomer(String userId);
    protected abstract ResponseEntity<?> createOrder(OrderCreateDTO orderDTO);
    protected abstract ResponseEntity<?> setOrderAction(String orderId, String actionCode, String userID, NoteDTO note);
    protected abstract List<AreaDTO> getAreaList(String cityCode);
    protected abstract List<ShipperOrderDTO> getShippersOrder(String shipperID);
    protected abstract ResponseEntity<?> getOrderByIdOrUser(String orderId, String userName);
}
