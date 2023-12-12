package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList,String> {

//    @Query("SELECT u FROM BlackList u WHERE u.user = :user")
//    List<BlackList> findBlackListByUser(@Param("user") User user);



}
