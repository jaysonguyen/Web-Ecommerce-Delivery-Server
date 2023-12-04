package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.BlackListDTO;
import com.example.deliveryecommercebackend.model.BlackList;
import com.example.deliveryecommercebackend.model.user.User;
import com.example.deliveryecommercebackend.repository.BlackListRepository;
import com.example.deliveryecommercebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BlackListService {
    @Autowired
    private BlackListRepository blacklistRepo;

    @Autowired
    private UserRepository userRepo;

    public List<BlackList> getBlackList(){
        try{
            var blackList = blacklistRepo.findAll();
           return  blackList;
        }catch (Exception ex){
            System.out.println("Get blacklist failed - Error" + ex);
            return  Collections.emptyList();
        }
    }
    public ResponseEntity<?> insertBlackList(BlackListDTO blackListDTO){
        User user = userRepo.findById(blackListDTO.getUser_id()).get();
        if(user == null){
            return  ResponseEntity.badRequest().body("User not found");
        }
        BlackList blackList = new BlackList();
        blackList.setBlacklist_id(blackListDTO.getId());
        blackList.setDes(blackListDTO.getDes());
        blackList.setCode(blackListDTO.getCode());
        blackList.setUser(user);
        blackList.set_active(true);
        try{
            BlackList checkSave = blacklistRepo.save(blackList);
            if(checkSave != null){
                return ResponseEntity.ok().body("Insert blacklist success");
            }
        }catch (Exception ex){
            return  ResponseEntity.badRequest().body("Error" + ex);
        }
        return ResponseEntity.badRequest().body("Insert failed");
    }
}
