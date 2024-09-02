package com.example.demo.controller;

import com.example.demo.request.EmailRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
public class MailController {


    private final EmailService emailService;

    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<ApiResponse> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmail(request.getTo(), request.getSubject(), request.getBody());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Email sent successfully!"));
    }

    @PostMapping("/send-email-with-attachment")
    public ResponseEntity<ApiResponse> sendEmailWithAttachment(@RequestBody EmailRequest request) throws IOException {
        if (request.getFile() == null || request.getFile().isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Attachment is required"));
        }

        String fileName = request.getFile().getOriginalFilename();
        if (fileName == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid file name"));
        }

        File attachment = File.createTempFile(fileName, ".tmp");
        request.getFile().transferTo(attachment);

        try {
            emailService.sendEmailWithAttachment(request.getTo(), request.getSubject(), request.getBody(), attachment);
            attachment.delete();
            return ResponseEntity.ok(new ApiResponse(true, "Email with attachment sent successfully!"));
        } catch (Exception e) {
            attachment.delete();
            return ResponseEntity.internalServerError().body(new ApiResponse(false, "Failed to send email with attachment"));
        }
    }
}
