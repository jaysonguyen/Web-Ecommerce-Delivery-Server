package com.example.deliveryecommercebackend.repository;


import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, String> {

    Branch findBranchByName(String name);

    @Query("SELECT u FROM Branch u WHERE u.is_delete = false")
    List<Branch> findNoneDeleteBranch();

}
