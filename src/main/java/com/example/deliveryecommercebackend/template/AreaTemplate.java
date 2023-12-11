package com.example.deliveryecommercebackend.template;

import com.example.deliveryecommercebackend.DTO.AreaCreatedDTO;
import com.example.deliveryecommercebackend.model.Area;
import com.example.deliveryecommercebackend.model.Branch;
import com.example.deliveryecommercebackend.model.ShippingAssignment;
import org.springframework.http.ResponseEntity;

public abstract class AreaTemplate {
    public ResponseEntity<?> createAreaAndAssignment(AreaCreatedDTO area){
        Area newArea = createArea(area);
        if(newArea == null) return ResponseEntity.badRequest().body("Create area failed");
        ShippingAssignment shippingAssignment = createAssignment(newArea);
        if(shippingAssignment == null) return ResponseEntity.badRequest().body("Create shipping assignment failed");
        return ResponseEntity.ok().body("Create area successfully");
    }
    protected abstract Area createArea(AreaCreatedDTO area);
    protected abstract ShippingAssignment createAssignment(Area area);
}
