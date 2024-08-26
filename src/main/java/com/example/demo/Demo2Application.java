package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Demo2Application {
    @Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {
        emailService.sendEmailWithAttachment("linux.vps.vpn@gmail.com", "hi", "test mail", "C:\\Users\\NIMA\\IdeaProjects\\demo2\\src\\main\\resources\\static\\myimage.png");
    }

}
