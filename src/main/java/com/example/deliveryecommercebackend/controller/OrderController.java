package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.OrderCreateDTO;
import com.example.deliveryecommercebackend.DTO.OrderDetailsDTO;
import com.example.deliveryecommercebackend.DTO.order.GetOrderListParams;
import com.example.deliveryecommercebackend.DTO.order.NoteDTO;
import com.example.deliveryecommercebackend.model.DateRange;
import com.example.deliveryecommercebackend.repository.CityRepository;
import com.example.deliveryecommercebackend.services.CityService;
import com.example.deliveryecommercebackend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    @PostMapping("/action/{actionCode}")
    @ResponseBody
    public ResponseEntity<?>getOrderListByAction(@PathVariable String actionCode,
                                                 @RequestHeader(name ="Authorization") String userID,
                                                 @RequestBody GetOrderListParams params) {
        try {
//            System.out.println(userID.split(" ")[1]);
            var listOrder = orderService.getAllOrderByAction(actionCode, userID.split(" ")[1], params);
            return listOrder;
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?>searchOrderByIdOrUser(@RequestParam("userName") String userName,
                                                  @RequestParam("orderId") String orderId ) {
        try {
            var check = orderService.getOrderByIdOrUser(orderId, userName);
            return check;
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }
    }
    @GetMapping("/city")
    @ResponseBody
    public ResponseEntity<?> getCityList() {
        try {
            var cityList = cityService.getCityListDropDown();
            return cityList;
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
            return listOrder;
        } catch (Exception ex) {
            System.out.println("Error at get orderByCode: " + ex.getMessage());
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
            var checkAdd = orderService.createOrder(order);
            return checkAdd;
        } catch (Exception ex) {
            System.out.println("Error from server, Error:" + ex);
            return ResponseEntity.badRequest().body("Error from order");
        }
    }

    @PutMapping("{orderId}/action/{actionCode}")
    public ResponseEntity<?> setActionOrder(@PathVariable String orderId,
                                            @RequestHeader(name ="Authorization") String userID,
                                            @PathVariable String actionCode,
                                            @RequestBody NoteDTO note
                                            ) {
        try {
            System.out.println(note.getNote());
            var check = orderService.setOrderAction(orderId, actionCode, userID.split(" ")[1], note);
            return check;
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex);
            return ResponseEntity.badRequest().body("Error from server" + ex);
        }
    }

    @GetMapping("/shipper/{shipperID}")
    public ResponseEntity<?> getShippersListOrder(@PathVariable String shipperID) {
        try {
            var getOrderList = orderService.getShippersOrder(shipperID);

            if(getOrderList != null) {
                return ResponseEntity.ok().body(getOrderList);
            }
            return ResponseEntity.badRequest().body("Not found user");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from body, Error: " + ex.getMessage());
        }
    }

}
