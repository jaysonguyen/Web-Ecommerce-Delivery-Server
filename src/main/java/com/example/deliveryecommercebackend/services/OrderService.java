package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.*;
import com.example.deliveryecommercebackend.DTO.order.NoteDTO;
import com.example.deliveryecommercebackend.model.*;
import com.example.deliveryecommercebackend.model.Order;
import com.example.deliveryecommercebackend.model.user.User;
import com.example.deliveryecommercebackend.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        System.out.println(order.getCity_code());
        //find city
        City city = cityRepo.findNoneDeleteCityByCode(order.getCity_code());
        if(city == null) {
            return ResponseEntity.badRequest().body("City not found");
        }

        //find area
        Area area = areaRepo.findByCode(order.getArea_code());
        if(area == null) {
            return ResponseEntity.badRequest().body("Area not found");
        }

        //find shipper
//        User shipper = userRepo.findNoneDeleteShipperByCode(order.getShipper_code());
//        if(city == null) {
//            return null;
//        }

        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(order, city.getName(), area.getName());

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



    public ResponseEntity<?> getOrderByCustomer(String userId) {
        //find user
        User user = userRepo.findUserById(userId);
        if(user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        //find order
        Order order = orderRepo.findOrderByCustomer(user);
        if(order == null) {
            return ResponseEntity.badRequest().body("Order not found");
        }

        //find city
        City city = cityRepo.findNoneDeleteCityByCode(order.getCity_code());
        if(city == null) {
            return ResponseEntity.badRequest().body("City not found");
        }

        //find area
        Area area = areaRepo.findByCode(order.getArea_code());
        if(area == null) {
            return ResponseEntity.badRequest().body("Area not found");
        }

        //find shipper
//        User shipper = userRepo.findNoneDeleteShipperByCode(order.getShipper_code());
//        if(city == null) {
//            return null;
//        }

        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(order, city.getName(), area.getName());

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

            //find order
            Order orderCheck = orderRepo.findOrderByCode(orderDTO.getOrder_code());
            if(orderCheck != null) {
                return ResponseEntity.badRequest().body("Order exists");
            }

            Order order = new Order();
            System.out.println(orderDTO.getTotal_cost());
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
            //find user update order
            User user = userRepo.findUserById(userID);
            if(user == null){
                return ResponseEntity.badRequest().body("User not found");
            }

            //add points if order is finished
            if(actionCode.equals("6") && !addPointsToUser(actionCode, user, order.getTotal_cost())) {
                userRepo.save(user);
                return ResponseEntity.badRequest().body("Cannot add points for user");
            }

            var checkSave = orderRepo.save(order);
            if(checkSave.getOrder_id() != null) {
                //save data to history

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

    private boolean addPointsToUser(String action_code, User user, double total) {
        switch(action_code) {
            case "6":
                int newPoints = (int) Math.round(user.getPoint() + (total / 1000));
                user.setPoint(newPoints);
                return true;
        }
        return false;
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

    public List<ShipperOrderDTO> getShippersOrder(String shipperCode) {
        try {

            var listOrder = orderRepo.findOrderByShipperAssigned(shipperCode);
            List<ShipperOrderDTO> spOr = new ArrayList<>();
            for (var i: listOrder) {
               ShipperOrderDTO spDTO = new ShipperOrderDTO();
               spDTO.setCustomerName(i.getUser().getFullName());
               spDTO.setOrders(i);
                spOr.add(spDTO);
            }
            return spOr;
        } catch (Exception ex) {
            System.out.println("Error from service, Error: " + ex);
            return Collections.emptyList();
        }
    }
}
