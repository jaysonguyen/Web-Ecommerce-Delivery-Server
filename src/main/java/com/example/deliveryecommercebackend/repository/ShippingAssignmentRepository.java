package com.example.deliveryecommercebackend.repository;

<<<<<<< HEAD
import com.example.deliveryecommercebackend.model.Area;
=======
>>>>>>> 52b639102fe01d07370680cd43178b2757f86186
import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.ShippingAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShippingAssignmentRepository extends JpaRepository<ShippingAssignment, String> {


    @Query("SELECT S FROM ShippingAssignment S WHERE S.branch = :branch")
    List<ShippingAssignment> findShippingAssignmentByBranch(@Param("branch")Branch branch);
<<<<<<< HEAD


    @Query("SELECT S FROM ShippingAssignment S WHERE  S.branch = :branch AND S.area = :area")
    ShippingAssignment findShippingAssignmentByAreaAndAndBranch(@Param("branch") Branch branch, @Param("area")Area area);
=======
>>>>>>> 52b639102fe01d07370680cd43178b2757f86186
}
