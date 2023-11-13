package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.DTO.BlackListDTO;
import com.example.deliveryecommercebackend.services.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/blacklist")
public class BlackListController {
    @Autowired
    private BlackListService blackListService;


    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getBlackList(){
        try{
            var blacklist = blackListService.getBlackList();
            if(blacklist != null) {
                System.out.println("get list success");
                return ResponseEntity.ok().body(blacklist);
            }
        }catch (Exception ex){
            System.out.println("Error from controller" + ex.getMessage());
        }
        return ResponseEntity.status(400).body("Error from controller:");

    }

    @PostMapping
    public  ResponseEntity<?> addToBlackList( @RequestBody BlackListDTO blackListDTO){

        try{
           var blacklist = blackListService.insertBlackList(blackListDTO);
           if(blacklist != null){
               return ResponseEntity.ok().body(blacklist);
           }else return ResponseEntity.badRequest().body("insert fail");
        }catch (Exception ex){
            System.out.println("Error" + ex);
        }
        return ResponseEntity.badRequest().body("Error");

    }
}
