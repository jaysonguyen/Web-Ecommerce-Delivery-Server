package com.example.deliveryecommercebackend.repository;


import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, String> {

    Branch findBranchByName(String name);

    @Query("SELECT u FROM Branch u WHERE u.is_delete = false")
    List<Branch> findNoneDeleteBranch();

    @Query("SELECT b FROM Branch b WHERE b.is_delete = false AND b.code = :code")
    Branch findBranchByCode(@Param("code") String code);

    @Query( "SELECT u FROM Branch u WHERE u.is_delete = false  ")
    List<Branch> findAllActiveBranchesDropdown();
    @Query( "SELECT u FROM Branch u WHERE u.city = :city  ")
    Branch findBranchByCity(@Param("city") City city);
}
