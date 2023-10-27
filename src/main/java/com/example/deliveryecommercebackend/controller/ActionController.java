package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.DTO.ActionDTO;
import com.example.deliveryecommercebackend.model.Action;
import com.example.deliveryecommercebackend.repository.ActionRepository;
import com.example.deliveryecommercebackend.services.ActionService;
import com.example.deliveryecommercebackend.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/action")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @GetMapping
    public List<Action> getActionList(){
        return actionService.getAllActions();
    }
    @PostMapping
    public ResponseEntity<?> createAction(@RequestBody ActionDTO action){
        try {
            HttpStatus checkAdd = actionService.createAction(action);
            if(checkAdd == HttpStatus.OK) {
                return ResponseEntity.status(200).body("Insert data successful");
            }
            return ResponseEntity.badRequest().body("Insert success");
        } catch (Exception ex) {
            System.out.println("Error from server, Error:" + ex);
            return ResponseEntity.badRequest().body("Error from action");
        }
    }
    @PutMapping("{action_id}")
    public ResponseEntity<?> updateAction(@PathVariable Integer action_id, @RequestBody ActionDTO action){
        try {
            HttpStatus checkAdd = actionService.updateAction(action_id, action);
            if(checkAdd == HttpStatus.OK) {
                return ResponseEntity.status(200).body("Update data successful");
            }
            return ResponseEntity.badRequest().body("Update success");
        } catch (Exception ex) {
            System.out.println("Error from server, Error:" + ex);
            return ResponseEntity.badRequest().body("Error from action");
        }
    }
    @DeleteMapping("{action_id}")
    public ResponseEntity<?> deleteAction(@PathVariable Integer action_id){
        try {
            HttpStatus checkAdd = actionService.deleteAction(action_id);
            if(checkAdd == HttpStatus.OK) {
                return ResponseEntity.status(200).body("Update data successful");
            }
            return ResponseEntity.badRequest().body("Update success");
        } catch (Exception ex) {
            System.out.println("Error from server, Error:" + ex);
            return ResponseEntity.badRequest().body("Error from action");
        }
    }
}
