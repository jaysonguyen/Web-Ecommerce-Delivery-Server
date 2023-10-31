package com.example.deliveryecommercebackend.repository;


import com.example.deliveryecommercebackend.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, String> {

    Branch findBranchByName(String name);
}
