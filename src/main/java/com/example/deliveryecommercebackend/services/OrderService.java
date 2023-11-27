package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.*;
import com.example.deliveryecommercebackend.DTO.order.NoteDTO;
import com.example.deliveryecommercebackend.DTO.order.ProductDTO;
import com.example.deliveryecommercebackend.model.*;
import com.example.deliveryecommercebackend.model.Order;
import com.example.deliveryecommercebackend.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private ActionRepository actionRepo;
    @Autowired
    private HistoryOrderRepository HistoryOrderRepository;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ProductTypeRepository productTypeRepo;
    @Autowired
    private CityRepository cityRepo;
    @Autowired
    private AreaRepository areaRepo;

    @Autowired
    private UserService userService;

    public ResponseEntity<?> getAllOrderByAction(String actionCode, String userID, DateRange dateRange) {
        try {
            ///find user
            User user = userRepo.findUserById(userID);
            if(user == null) {
                return ResponseEntity.badRequest().body("User not found");
            }

            // find action match
            Action action = actionRepo.findActionByCode(actionCode);
            if(action == null) {
                return ResponseEntity.badRequest().body("Action not found");
            }

            //check orders exists
            List<Order> orders = new ArrayList<>();
            if(Objects.equals(user.getRole().getName(), "admin")) {
                orders = orderRepo.findOrderByAction(actionCode, dateRange.getStart(), dateRange.getEnd());
            } else {
                orders = orderRepo.findOrderByActionAndUser(actionCode, user, dateRange.getStart(), dateRange.getEnd());
            }
            if(orders == null) {
                return ResponseEntity.badRequest().body("Order not found");
            }

            List<OrderDisplayListDTO> res = new ArrayList<OrderDisplayListDTO>();
            for(Order order : orders){
                OrderDisplayListDTO temp = new OrderDisplayListDTO(order, action.getName());
                res.add(temp);
            }

            return ResponseEntity.ok().body(res);
        } catch(Exception ex) {
            System.out.printf("Get order list failed - Error: " + ex);
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }

    public ResponseEntity<?> getOrderByCode(String orderCode) {
        //find order
        Order order = orderRepo.findOrderByCode(orderCode);
        System.out.println(order);
        if(order == null) {
            return ResponseEntity.badRequest().body("Order not found");
        }

        //find shipper
//        User shipper = userRepo.findNoneDeleteShipperByCode(order.getShipper_code());
//        if(city == null) {
//            return null;
//        }

        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(order);

        try {
            var checkSave = orderRepo.save(order);
            if(checkSave != null) {
                return ResponseEntity.ok().body(orderDetailsDTO);
            } else {
                return ResponseEntity.badRequest().body("Get order failed");
            }
        } catch (Exception ex) {
            System.out.printf("Error from service: " + ex);
            return ResponseEntity.badRequest().body("Error from service: " + ex.getMessage());

        }
    }

    public ResponseEntity<?> createOrder(OrderCreateDTO orderDTO) {
        try {
            //find user
            User user = userRepo.getUserById(orderDTO.getUser_id());
            if(user == null) {
                return ResponseEntity.badRequest().body("User not found");
            }

            Order order = new Order();
            order.setDataCreate(orderDTO, user);
            var checkSave = orderRepo.save(order);
            if(checkSave != null) {
                return ResponseEntity.ok().body("Created successfully");
            } else {
                return ResponseEntity.badRequest().body("Created failed");
            }
        } catch (Exception ex) {
            System.out.printf("Error from service: " + ex);
            return ResponseEntity.status(500).body("Server error: " + ex.getMessage());
        }

    }

    public ResponseEntity<?> setOrderAction(String orderId, String actionCode, String userID, NoteDTO note) {
        System.out.println(note);

        //find order
        Order order = orderRepo.findOrderById(orderId);
        order.setAction_code(actionCode);

        try {
            var checkSave = orderRepo.save(order);
            if(checkSave.getOrder_id() != null) {
                //save data to history
                //find user update order
                User user = userRepo.findUserById(userID);
                if(user == null){
                    return ResponseEntity.badRequest().body("User not found");
                }

                HistoryOrder history;
                //if shipper set action for order
                if(user.getRole().getName().equals("shipper")){
                    history = new HistoryOrder(order, user, user.getFullName(), actionCode, note.getNote());
                }
                else {
                    history = new HistoryOrder(order, user, "", actionCode, note.getNote());
                }

                var checkSave2 = HistoryOrderRepository.save(history);
                if(checkSave2.getOrder_id() != null) {
                return ResponseEntity.ok().body("Set action successfully");
                }

                return ResponseEntity.badRequest().body("Set history failed");
            } else {
                return ResponseEntity.badRequest().body("Set action failed");
            }
        } catch (Exception ex) {
            System.out.printf("Error from service: " + ex);
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }

    }

    public List<AreaDTO> getAreaList(String cityCode) {
        try {
            City city = cityRepo.findNoneDeleteCityByCode(cityCode);

            //check orders exists
            List<Area> areaList = areaRepo.findNoneDeleteAreaByCity(city);
            List<AreaDTO> areaDTOS = new ArrayList<>();
            for(Area area : areaList){
                AreaDTO temp = new AreaDTO(area);
                areaDTOS.add(temp);
            }

            return areaDTOS;
        } catch(Exception ex) {
            System.out.printf("Get area list failed - Error: " + ex);
            return Collections.emptyList();
        }
    }
}
