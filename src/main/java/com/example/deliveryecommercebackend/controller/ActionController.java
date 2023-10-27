package com.example.deliveryecommercebackend.controller;

import com.example.deliveryecommercebackend.model.Action;
import com.example.deliveryecommercebackend.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/action")
public class ActionController {

    @Autowired
    private ActionRepository actionRepository;

    @GetMapping
    public List<Action> getActionList(){
        return actionRepository.findAll();
    }
    @PostMapping
    public List<Action> createAction(){
        return actionRepository.findAll();
    }
}
