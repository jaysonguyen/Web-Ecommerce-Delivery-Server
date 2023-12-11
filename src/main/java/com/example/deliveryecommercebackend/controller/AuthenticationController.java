package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.LoginDTO;
import com.example.deliveryecommercebackend.advice.LoginInterceptor;
import com.example.deliveryecommercebackend.services.AuthenticationServices;
import com.example.deliveryecommercebackend.services.MailSenderServices;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationServices authenticationServices;
    @Autowired
    private MailSenderServices mailSenderServices;

    public AuthenticationController(AuthenticationServices authenticationServices) {
        this.authenticationServices = authenticationServices;
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginObject) {
        try {
            ProxyFactory factory = new ProxyFactory(authenticationServices);
            //factory.addAdvice(loginInterceptor);

            AuthenticationServices myService = (AuthenticationServices) factory.getProxy();
            var checkLogin = myService.loginUser(loginObject);
            return checkLogin;
        }
        catch (Exception ex) {
            System.out.printf("Error from server" + ex);
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

    @PostMapping("/generate/otp/{email}")
    public ResponseEntity<?> generateOTP(@PathVariable String email) {
        try {
            int info = mailSenderServices.generateOTP(email);
            return ResponseEntity.ok().body(info);
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
            return ResponseEntity.badRequest().body("Error from server");
        }
    }

}
