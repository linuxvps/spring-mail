package com.example.mail;

import com.example.mail.service.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.File;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MailApplication {
    private final EmailService emailService;

    public MailApplication(EmailService emailService) {
        this.emailService = emailService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {

        if (false) {
            String path = "C:\\Users\\NIMA\\IdeaProjects\\demo2\\src\\main\\resources\\static\\myimage.png";
            emailService.sendEmailWithAttachment("linux.vps.vpn@gmail.com", "hi", "test mail", getFile(path));
            emailService.sendEmailWithAttachment("linux.vps.vpn@gmail.com", "hi", "test mail", getFile(path));
        }
    }

    private static File getFile(String path) {
        return new File(path);
    }

}
