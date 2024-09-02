package com.example.demo.service;

import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Part;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(EmailService.class.getName());
    }


    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        System.out.println("mail sent");
    }

    public void sendEmailWithAttachment(String to, String subject, String body, File file) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            helper.addAttachment(file.getName(),file);

            mailSender.send(message);
            logAttachmentNames(message);
            System.out.println("Email with attachment sent successfully!");

        } catch (Exception e) {
            System.out.println("Email failed!");
        }
    }

    public void logAttachmentNames(MimeMessage message) {
        try {
            Object content = message.getContent();

            if (content instanceof Multipart) {
                Multipart multipart = (Multipart) content;
                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart bodyPart = multipart.getBodyPart(i);
                    String disposition = bodyPart.getDisposition();

                    if (Part.ATTACHMENT.equalsIgnoreCase(disposition) && bodyPart.getFileName() != null) {
                        LOGGER.log(Level.SEVERE, "Attachment name: {0}", bodyPart.getFileName());
                    }
                }
            } else {
                LOGGER.log(Level.SEVERE, "Message content is not a multipart");
            }
        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, "Failed to process the email message", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO Error occurred while processing the email message", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error occurred", e);
        }
    }

}