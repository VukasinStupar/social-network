package com.example.isaProject.serviceImpl;


import com.example.isaProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;



    public void sendActivationCode(User user) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Account activation");


        mail.setText("Welcome to app");

        javaMailSender.send(mail);
    }



}