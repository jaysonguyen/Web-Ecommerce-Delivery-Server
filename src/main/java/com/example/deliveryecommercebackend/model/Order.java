package com.example.deliveryecommercebackend.model;
import com.example.deliveryecommercebackend.DTO.OrderCreateDTO;
import com.example.deliveryecommercebackend.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="delivery_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String order_id;
    @Column(name = "code", unique = true, nullable = false)
    private String order_code; //new

    private String action_code;
    private String city_code;
    private String area_code;

    private double ship_cost;

    //json
    @Column(columnDefinition = "TEXT")
    private String receiver;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date updated;

    @Column(columnDefinition = "TEXT")
    private String address;


    // json
    @Column(columnDefinition = "TEXT")
    private String product;
    // json
    @Column(columnDefinition = "TEXT")
    private String package_order;

    // shipper cost
    private double total_cost;
    // cost of package
    private double cost;
    private double voucher_discount; //new
    private boolean collect_money;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    //user code
    private String shipper_id;


    public void setDataCreate(OrderCreateDTO orderDTO, User user) {
        this.user = user;

        this.order_code = orderDTO.getOrder_code();
        this.action_code = orderDTO.getAction_code();
        this.product = orderDTO.getProduct();
        this.city_code = orderDTO.getCity_code();
        this.area_code = orderDTO.getArea_code();
        this.address = orderDTO.getAddress();
        this.package_order = orderDTO.getPackage_order();
//        this.ship_cost = orderDTO.getShip_cost();
        this.receiver = orderDTO.getReceiver();

        this.total_cost = orderDTO.getTotal_cost();
        this.cost = orderDTO.getCost();
        this.voucher_discount = orderDTO.getVoucher_discount();

        this.created = new Date();
        this.updated = new Date();
    }
}
