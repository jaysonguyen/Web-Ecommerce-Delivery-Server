package com.example.deliveryecommercebackend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MailSenderServices {

    @Autowired
    private JavaMailSender sender;
    public void sendMail(String subject, String email, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("01216571415kt@gmail.com");
        message.setTo(email);
        message.setText(text);
        message.setSubject(subject);
        sender.send(message);
    }


    public int generateOTP(String email) {
        Random rd = new Random();
        String subject = "[HUFLIT DELIVERY] OTP CONFIRMATION";
        int randomNumber = rd.nextInt(1000, 9999);
        String text = "Your OTP: " + randomNumber;
        sendMail(subject, email, text);
        return randomNumber;
    }
}
