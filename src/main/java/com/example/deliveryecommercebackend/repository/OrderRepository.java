package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.DTO.chart.DataQuery;
import com.example.deliveryecommercebackend.model.Order;
import com.example.deliveryecommercebackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT u FROM Order u WHERE u.created BETWEEN :start AND :end")
    List<Order> getOrderByDate(@Param("start") Date start, @Param("end") Date end);

    @Query("SELECT new com.example.deliveryecommercebackend.DTO.chart.DataQuery(count(u), u.created, u.action_code) FROM Order u " +
            "WHERE function('date_format', u.created,'%m-%d-%Y') BETWEEN function('date_format', :start, '%m-%d-%Y') AND function('date_format', :end, '%m-%d-%Y') " +
            "GROUP BY u.created, u.action_code")
    ArrayList<DataQuery> getSumOrderByDateAndAction(@Param("start") Date start, @Param("end") Date end);

    @Query("SELECT u FROM Order u WHERE u.order_id = :id")
    Order findOrderById(@Param("id") String orderId);

    @Query("SELECT u FROM Order u WHERE u.action_code = :action_code AND " +
            "function('date_format', u.created,'%m-%d-%Y') BETWEEN function('date_format', :start,'%m-%d-%Y') AND " +
            "function('date_format', :end,'%m-%d-%Y') AND " +
            "(:cityCode = '' OR u.city_code = :cityCode) AND " +
            "(:areaCode = '' OR u.area_code = :areaCode)")
    List<Order> findOrderByAction(@Param("action_code") String action_code,
                                  @Param("start") Date dateStart,
                                  @Param("end") Date dateEnd,
                                  @Param("cityCode") String city_code,
                                  @Param("areaCode") String area_code
                                  );

    @Query("SELECT u FROM Order u WHERE u.order_code = :code")
    Order findOrderByCode(@Param("code") String orderCode);
    @Query("SELECT u FROM Order u WHERE u.action_code = :action_code AND u.user = :user AND " +
            "function('date_format', u.created,'%m-%d-%Y') BETWEEN function('date_format', :start,'%m-%d-%Y') AND " +
            "function('date_format', :end,'%m-%d-%Y') AND " +
            "(:cityCode = '' OR u.city_code = :cityCode) AND " +
            "(:areaCode = '' OR u.area_code = :areaCode)")
    List<Order> findOrderByActionAndUser(@Param("action_code") String actionCode,
                                         @Param("user") User user,
                                         @Param("start") Date dateStart,
                                         @Param("end") Date dateEnd,
                                         @Param("cityCode") String city_code,
                                         @Param("areaCode") String area_code
    );
    @Query("SELECT u FROM Order u WHERE u.user = :user")
    Order findOrderByCustomer(@Param("user") User userId);
    @Query("SELECT u FROM Order u WHERE u.order_id LIKE CONCAT('%',:orderId,'%')")
    List<Order> findOrderLikeId(@Param("orderId") String orderId);

    @Query("SELECT u FROM Order u WHERE u.shipper_id= :shipperID")
    List<Order> findOrderByShipperAssigned(@Param("shipperID") String shipperID);
}
