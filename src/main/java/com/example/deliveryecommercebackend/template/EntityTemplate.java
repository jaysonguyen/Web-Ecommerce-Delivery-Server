package com.example.deliveryecommercebackend.template;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class EntityTemplate<T> {
    public ResponseEntity<?> performCURD(T entity, String command) {
        try {
            if(!validateInput(entity)){
                return ResponseEntity.badRequest().body("Inputs didn't valid");
            }
            boolean result = false;
            switch (command) {
                case "create" -> {
                    result = create(entity);
                }
                case "update" -> {
                    result = update(entity);
                }
                case "delete" -> {
                    result = delete(entity);
                }
                default -> {
                    return ResponseEntity.badRequest().body("Command was not valid");
                }
            }
            if(!result) return ResponseEntity.badRequest().body(command + " failed");
            return ResponseEntity.ok().body(command + " successful");
        } catch (Exception ex) {
            System.out.printf("Error from service - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error from service");
        }
    }

    // Hook methods to be overridden by subclasses
    protected abstract boolean validateInput(T entity);
    protected abstract boolean create(T entity);
    protected abstract boolean update(T entity);
    protected abstract boolean delete(T entity);
    protected abstract List<?> getAll();
    protected abstract T getByID(String id);
}
