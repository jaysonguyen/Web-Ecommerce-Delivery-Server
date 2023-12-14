package com.example.deliveryecommercebackend.controller;
import com.example.deliveryecommercebackend.DTO.OrderCreateDTO;
import com.example.deliveryecommercebackend.DTO.order.GetOrderListParams;
import com.example.deliveryecommercebackend.DTO.order.NoteDTO;
import com.example.deliveryecommercebackend.controller.OrderControllerFacade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderFacade orderFacade;

    @Autowired
    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @PostMapping("/action/{actionCode}")
    @ResponseBody
    public ResponseEntity<?> getOrderListByAction(@PathVariable String actionCode,
                                                  @RequestHeader(name ="Authorization") String userID,
                                                  @RequestBody GetOrderListParams params) {
        return orderFacade.getOrderListByAction(actionCode, userID.split(" ")[1], params);
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> searchOrderByIdOrUser(@RequestParam("userName") String userName,
                                                   @RequestParam("orderId") String orderId) {
        return orderFacade.searchOrderByIdOrUser(userName, orderId);
    }

    @GetMapping("/city")
    @ResponseBody
    public ResponseEntity<?> getCityList() {
        return orderFacade.getCityList();
    }

    @GetMapping("/city/{cityCode}/area")
    @ResponseBody
    public ResponseEntity<?> getAreaList(@PathVariable String cityCode) {
        return orderFacade.getAreaList(cityCode);
    }

    @GetMapping("/{orderCode}")
    @ResponseBody
    public ResponseEntity<?> getOrderByCode(@PathVariable String orderCode) {
        return orderFacade.getOrderByCode(orderCode);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateDTO order) {
        return orderFacade.createOrder(order);
    }

    @PutMapping("{orderId}/action/{actionCode}")
    public ResponseEntity<?> setActionOrder(@PathVariable String orderId,
                                            @RequestHeader(name ="Authorization") String userID,
                                            @PathVariable String actionCode,
                                            @RequestBody NoteDTO note) {
        return orderFacade.setActionOrder(orderId, actionCode, userID.split(" ")[1], note);
    }

    @GetMapping("/shipper/{shipperID}")
    public ResponseEntity<?> getShippersListOrder(@PathVariable String shipperID) {
        return orderFacade.getShippersListOrder(shipperID);
    }
}