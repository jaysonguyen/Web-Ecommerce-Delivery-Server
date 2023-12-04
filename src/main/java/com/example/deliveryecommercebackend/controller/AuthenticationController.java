package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.LoginDTO;
import com.example.deliveryecommercebackend.advice.LoginInterceptor;
import com.example.deliveryecommercebackend.services.AuthenticationServices;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationServices authenticationServeces;

    public AuthenticationController(AuthenticationServices authenticationServeces) {
        this.authenticationServeces = authenticationServeces;
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginObject) {
        try {
            LoginInterceptor loginInterceptor = new LoginInterceptor();
            ProxyFactory factory = new ProxyFactory(authenticationServeces);
            factory.addAdvice(loginInterceptor);

            AuthenticationServices myService = (AuthenticationServices) factory.getProxy();

            var checkLogin = myService.loginUser(loginObject);
            return checkLogin;
        }
        catch (Exception ex) {
            System.out.printf("Error from server" + ex);
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

}
