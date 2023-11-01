package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, String> {
    @Query("SELECT u FROM City u WHERE u.is_delete = false")
    List<City> findNoneDeleteCity();
    @Query("SELECT u FROM City u WHERE u.id = :id")
    City findNoneDeleteCityById(@Param("id") String id);
}
