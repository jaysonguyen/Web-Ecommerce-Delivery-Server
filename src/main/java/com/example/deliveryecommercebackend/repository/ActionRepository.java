package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Long> {
    //crud method

}
