package com.example.mail.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record EmailRequest(
        @NotBlank(message = "Recipient email is mandatory")
        @Email(message = "Invalid email format")
        String to,

        @NotBlank(message = "Subject is mandatory")
        @Size(max = 100, message = "Subject cannot be longer than 100 characters")
        String subject,

        @NotBlank(message = "Body is mandatory")
        String body,

        MultipartFile file
) {}
