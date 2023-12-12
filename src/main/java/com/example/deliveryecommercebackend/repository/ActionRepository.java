package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActionRepository extends JpaRepository<Action, Integer> {
    @Query("SELECT u FROM Action u WHERE u.code = :code")
    Action findActionByCode(@Param("code") String actionCode);
    //crud method

}
