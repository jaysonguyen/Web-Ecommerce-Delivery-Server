package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.model.DateRange;
import com.example.deliveryecommercebackend.repository.OrderRepository;
import com.example.deliveryecommercebackend.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @PostMapping("order/city/{cityCode}")
    public ResponseEntity<?> getOrderReport(@PathVariable String cityCode, @RequestBody DateRange dateRange){
        try{
            return reportService.orderReport(dateRange, cityCode);
        } catch(Exception ex){
            System.out.println("Error from get order report - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }
}
