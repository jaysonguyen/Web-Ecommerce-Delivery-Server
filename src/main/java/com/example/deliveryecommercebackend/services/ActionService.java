package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.ActionDTO;
import com.example.deliveryecommercebackend.exception.ResourceNotfoundException;
import com.example.deliveryecommercebackend.model.Action;
import com.example.deliveryecommercebackend.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public List<Action> getAllActions() {
        try {
            List<Action> actions = actionRepository.findAll();
            return actions;
        } catch(Exception ex) {
            System.out.printf("Get user failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public HttpStatus createAction(ActionDTO action) {
        Action newAction = new Action();

        newAction.setName(action.getName());
        newAction.setDes(action.getDes());
        newAction.setType(action.getType());
        newAction.setCreated(Date.valueOf(LocalDate.now()));
        newAction.set_deleted(false);

        try {
            actionRepository.save(newAction);
            return HttpStatus.OK;
        } catch(Exception ex) {
            System.out.printf("Create action failed - Error" + ex);
            return HttpStatus.BAD_REQUEST;
        }
    }

    public HttpStatus updateAction(Integer code, ActionDTO action) {
        Action newAction = actionRepository.findById(code)
                .orElseThrow(() -> new ResourceNotfoundException("Action not exist with code: " + code));

        newAction.setName(action.getName());
        newAction.setDes(action.getDes());
        newAction.setType(action.getType());

        try {
            actionRepository.save(newAction);
            return HttpStatus.OK;
        } catch(Exception ex) {
            System.out.printf("Create action failed - Error" + ex);
            return HttpStatus.BAD_REQUEST;
        }
    }

    public HttpStatus deleteAction(Integer code) {
        Action newAction = actionRepository.findById(code)
                .orElseThrow(() -> new ResourceNotfoundException("Bank not exist with code: " + code));

        newAction.set_deleted(true);

        try {
            actionRepository.save(newAction);
            return HttpStatus.OK;
        } catch(Exception ex) {
            System.out.printf("Create action failed - Error" + ex);
            return HttpStatus.BAD_REQUEST;
        }
    }

}
