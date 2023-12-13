//package com.example.deliveryecommercebackend.Data;
//
//import com.example.deliveryecommercebackend.repository.AreaRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AreaSingleton {
//
//    public static AreaSingleton Instance;
//    public List<AreaSingleton> listArea;
//    private AreaSingleton() {}
//
//    public void Init(AreaRepository areaRepo, String area_id) {
//        if(listArea.stream().count() == 0) {
//            var listArea = areaRepo.findNoneDeleteAreaById(area_id);
//            for (var item: listArea
//                 ) {
//                listArea.add(item);
//
//            }
//        }
//    }
//}
