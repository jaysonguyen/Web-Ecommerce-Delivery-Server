package com.example.deliveryecommercebackend.Data;

import com.example.deliveryecommercebackend.model.Area;
import com.example.deliveryecommercebackend.services.AreaService;

import java.util.LinkedList;
import java.util.List;

public class AreaSingleton {
    private static AreaService instance;
    private List<Area> listArea = new LinkedList<>();
    private AreaSingleton() {
    }
    public static synchronized AreaService getInstance() {
        if (instance == null) {
            instance = new AreaService();
        }
        return instance;
    }
}