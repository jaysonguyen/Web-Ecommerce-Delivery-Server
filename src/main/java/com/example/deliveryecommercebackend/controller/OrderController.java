package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.OrderCreateDTO;
import com.example.deliveryecommercebackend.DTO.OrderDetailsDTO;
import com.example.deliveryecommercebackend.repository.CityRepository;
import com.example.deliveryecommercebackend.services.CityService;
import com.example.deliveryecommercebackend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CityService cityService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/action/{actionCode}")
    @ResponseBody
    public ResponseEntity<?>getOrderListByAction(@PathVariable String actionCode) {
        try {
            var listOrder = orderService.getAllOrderByAction(actionCode);
            return ResponseEntity.ok().body(listOrder);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }

    }
    @GetMapping("/city")
    @ResponseBody
    public ResponseEntity<?> getCityList() {
        try {
            var cityList = cityService.getCityListDropDown();
            return ResponseEntity.ok().body(cityList);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

    @GetMapping("/city/{cityCode}/area")
    @ResponseBody
    public ResponseEntity<?>getAreaList(@PathVariable String cityCode) {
        try {
            var areaList = orderService.getAreaList(cityCode);
            return ResponseEntity.ok().body(areaList);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }


    @GetMapping("/{orderCode}")
    @ResponseBody
    public ResponseEntity<?>getOrderByCode(@PathVariable String orderCode) {
        try {
            var listOrder = orderService.getOrderByCode(orderCode);
            if(listOrder == null) {
                return ResponseEntity.badRequest().body("List null");
            }
            return ResponseEntity.ok().body(listOrder);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }

    }

//    @GetMapping("/store/{orderId}")
//    @ResponseBody
//    public ResponseEntity<?> getStoreList(@PathVariable String orderId) {
//        try {
//            var storeList = orderService.getStoreByOrder(orderId);
//            if(storeList != null) {
//                return ResponseEntity.ok(storeList);
//            }
//        } catch (Exception ex) {
//            System.out.printf("Error from server");
//        }
//        return ResponseEntity.badRequest().body("Get list store failed");
//    }

//    @GetMapping("{order_id}")
//    public ResponseEntity<?>getOrderById(@PathVariable String order_id) {
//        try {
//            var order = orderService.getOrderById(order_id);
//            if (order.getId() == null) {
//                return ResponseEntity.ok().body("Order not found.");
//            } else {
//                return ResponseEntity.ok().body(order);
//            }
//        } catch (Exception ex) {
//            return ResponseEntity.badRequest().body("Error from server");
//        }
//
//    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateDTO order) {
        try {
            HttpStatus checkAdd = orderService.createOrder(order);
            if(checkAdd == HttpStatus.OK) {
                return ResponseEntity.ok("Insert success");
            } else {
                return ResponseEntity.status(checkAdd).body("Insert order failed");
            }
        } catch (Exception ex) {
            System.out.println("Error from server, Error:" + ex);
            return ResponseEntity.badRequest().body("Error from order");
        }
    }

    @PutMapping("{orderCode}/action/{actionCode}")
    public ResponseEntity<?> setActionOrder(@PathVariable String orderCode, @PathVariable String actionCode) {
        try {
            HttpStatus check = orderService.setOrderAction(orderCode, actionCode);
            if(check != HttpStatus.OK)
                return ResponseEntity.status(check).body("Set action failed");
            return ResponseEntity.status(check).body("Set action successfully");
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex);
            return ResponseEntity.badRequest().body("Error from server" + ex);
        }
    }

}
