package com.example.deliveryecommercebackend.controller.OrderControllerFacade;
import com.example.deliveryecommercebackend.DTO.OrderCreateDTO;
import com.example.deliveryecommercebackend.DTO.order.GetOrderListParams;
import com.example.deliveryecommercebackend.DTO.order.NoteDTO;
import com.example.deliveryecommercebackend.model.DateRange;
import com.example.deliveryecommercebackend.services.CityService;
import com.example.deliveryecommercebackend.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class OrderFacade {

    private final OrderService orderService;
    private final CityService cityService;
    private final Logger logger = LoggerFactory.getLogger(OrderFacade.class);

    @Autowired
    public OrderFacade(OrderService orderService, CityService cityService) {
        this.orderService = orderService;
        this.cityService = cityService;
    }

    public ResponseEntity<?> getOrderListByAction(String actionCode, String userID, GetOrderListParams params) {
        try {
            logger.info("Received request to get order list by action. Action Code: {}, User ID: {}", actionCode, userID);
            return orderService.getAllOrderByAction(actionCode, userID, params);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

    public ResponseEntity<?> searchOrderByIdOrUser(String userName, String orderId) {
        try {
            return orderService.getOrderByIdOrUser(orderId, userName);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

    public ResponseEntity<?> getCityList() {
        try {
            return cityService.getCityListDropDown();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

    public ResponseEntity<?> getAreaList(String cityCode) {
        try {
            return (ResponseEntity<?>) orderService.getAreaList(cityCode);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

    public ResponseEntity<?> getOrderByCode(String orderCode) {
        try {
            return orderService.getOrderByCode(orderCode);
        } catch (Exception ex) {
            logger.error("Error at getOrderByCode: {}", ex.getMessage());
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

    public ResponseEntity<?> createOrder(OrderCreateDTO order) {
        try {
            return orderService.createOrder(order);
        } catch (Exception ex) {
            logger.error("Error from server: {}", ex.getMessage());
            return ResponseEntity.badRequest().body("Error from order");
        }
    }

    public ResponseEntity<?> setActionOrder(String orderId, String actionCode, String userID, NoteDTO note) {
        try {
            logger.info("Setting action for order {}. Action Code: {}, User ID: {}", orderId, actionCode, userID);
            return orderService.setOrderAction(orderId, actionCode, userID, note);
        } catch (Exception ex) {
            logger.error("Error from controller: {}", ex.getMessage());
            return ResponseEntity.badRequest().body("Error from server: " + ex.getMessage());
        }
    }

    public ResponseEntity<?> getShippersListOrder(String shipperID) {
        try {
            var getOrderList = orderService.getShippersOrder(shipperID);
            if (getOrderList != null) {
                return ResponseEntity.ok().body(getOrderList);
            }
            return ResponseEntity.badRequest().body("Not found user");
        } catch (Exception ex) {
            logger.error("Error from body: {}", ex.getMessage());
            return ResponseEntity.badRequest().body("Error from body, Error: " + ex.getMessage());
        }
    }
}
