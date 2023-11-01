package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Area;
import com.example.deliveryecommercebackend.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, String> {
    @Query("SELECT u FROM Area u WHERE u.is_delete = false")
    List<Area> findNoneDeleteArea();
    @Query("SELECT u FROM Area u WHERE u.id = :id AND u.is_delete = false")
    Area findNoneDeleteAreaById(@Param("id") String id);

    @Query("SELECT u FROM Area u WHERE u.is_delete = false AND u.city = :city")
    List<Area> findNoneDeleteAreaByCity(@Param("city") City city);
}
