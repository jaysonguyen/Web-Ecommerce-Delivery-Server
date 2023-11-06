package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType, String> {
    @Query("SELECT u FROM ProductType u WHERE u.state = false")
    List<ProductType> findNoneDeleteProductType();
    @Query("SELECT u FROM ProductType u WHERE u.id = :id AND u.state = false")
    ProductType findNoneDeleteProductTypeById(@Param("id") String id);
    @Query("SELECT u FROM ProductType u WHERE u.code = :code AND u.state = false")

    ProductType findNoneDeleteProductTypeByCode(@Param("code") String code);

}
