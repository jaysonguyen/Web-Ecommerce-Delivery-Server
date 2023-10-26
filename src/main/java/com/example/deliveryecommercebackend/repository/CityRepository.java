package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, String> {
}
