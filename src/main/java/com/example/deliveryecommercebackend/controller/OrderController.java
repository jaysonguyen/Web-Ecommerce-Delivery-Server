package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{orderId}/action/{actionCode}")
    @ResponseBody
    public ResponseEntity<?>getOrderListByAction(@PathVariable String orderId, @PathVariable String actionCode) {
        try {
            var listOrder = orderService.getAllOrderByAction(orderId, actionCode);
            if (listOrder.isEmpty()) {
                return ResponseEntity.ok().body("Empty list order.");
            } else {
                return ResponseEntity.ok().body(listOrder);
            }
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

//    @PostMapping
//    public ResponseEntity<?> createOrder(@RequestBody OrderDTO order) {
//        try {
//            HttpStatus checkAdd = orderService.createOrder(order);
//            if(checkAdd == HttpStatus.OK) {
//                return ResponseEntity.ok("Insert success");
//            } else {
//                return ResponseEntity.status(checkAdd).body("Insert order failed");
//            }
//        } catch (Exception ex) {
//            System.out.println("Error from server, Error:" + ex);
//            return ResponseEntity.badRequest().body("Error from order");
//        }
//    }

//    @PutMapping
//    public ResponseEntity<?> updateOrderFromAdmin(@RequestBody OrderDTO order) {
//        try {
//            HttpStatus check = orderService.updateOrder(order);
//            if(check != HttpStatus.OK)
//                return ResponseEntity.status(check).body("Update data failed");
//            return ResponseEntity.status(check).body("Update data successfully");
//        } catch (Exception ex) {
//            System.out.printf("Error from controller" + ex);
//            return ResponseEntity.badRequest().body("Error fom server" + ex);
//        }
//    }

}
