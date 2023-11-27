package com.example.deliveryecommercebackend.services;


import com.example.deliveryecommercebackend.DTO.*;
import com.example.deliveryecommercebackend.DTO.order.ProductDTO;
import com.example.deliveryecommercebackend.model.*;
import com.example.deliveryecommercebackend.model.Order;
import com.example.deliveryecommercebackend.repository.*;
import jakarta.transaction.Transactional;
import net.bytebuddy.description.type.TypeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ProductTypeRepository productTypeRepo;
    @Autowired
    private CityRepository cityRepo;
    @Autowired
    private AreaRepository areaRepo;

    public List<OrderDisplayListDTO> getAllOrderByAction(String actionCode) {
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
                ProductType productType = productTypeRepo.findNoneDeleteProductTypeByCode(order.getProduct_type_code());
                OrderDisplayListDTO temp = new OrderDisplayListDTO(order, action.getName(), productType.getName());
                res.add(temp);
            }

            return res;
        } catch(Exception ex) {
            System.out.printf("Get order list failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public OrderDetailsDTO getOrderByCode(String orderCode) {
        //find order
        Order order = orderRepo.findOrderByCode(orderCode);
        System.out.println(order);
        if(order == null) {
            return null;
        }

        //find product type
        ProductType productType = productTypeRepo.findNoneDeleteProductTypeByCode(order.getProduct_type_code());
        System.out.println(productType);
        if(productType == null) {
            return null;
        }

        //find shipper
//        User shipper = userRepo.findNoneDeleteShipperByCode(order.getShipper_code());
//        if(city == null) {
//            return null;
//        }

        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(order, productType.getName() );

        try {
            var checkSave = orderRepo.save(order);
            if(checkSave != null) {
                return orderDetailsDTO;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.printf("Error from service: " + ex);
            return null;
        }
    }

    public HttpStatus createOrder(OrderCreateDTO orderDTO) {
        //find user
        User user = userRepo.findUserById(orderDTO.getUser_id());
        if(user == null) {
            return HttpStatus.BAD_REQUEST;
        }

        Order order = new Order();
        order.setDataCreate(orderDTO, user);

        try {
            var checkSave = orderRepo.save(order);
            if(checkSave != null) {
                return HttpStatus.OK;
            } else {
                return HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            System.out.printf("Error from service: " + ex);
            return HttpStatus.BAD_REQUEST;
        }

    }

    public HttpStatus setOrderAction(String orderCode, String actionCode) {
        //find order
        Order order = orderRepo.findOrderByCode(orderCode);
        order.setAction_code(actionCode);

        try {
            var checkSave = orderRepo.save(order);
            if(checkSave != null) {
                return HttpStatus.OK;
            } else {
                return HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            System.out.printf("Error from service: " + ex);
            return HttpStatus.BAD_REQUEST;
        }

    }

    public List<CityDTO> getCityList() {
        try {
            //check orders exists
            List<City> cityList = cityRepo.findNoneDeleteCity();
            List<CityDTO> cityDTOS = new ArrayList<>();
            for(City city : cityList){
                CityDTO temp = new CityDTO(city);
                cityDTOS.add(temp);
            }

            return cityDTOS;
        } catch(Exception ex) {
            System.out.printf("Get order list failed - Error: " + ex);
            return Collections.emptyList();
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
