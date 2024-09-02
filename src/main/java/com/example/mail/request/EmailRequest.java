package com.example.mail.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    @NotBlank(message = "Recipient email is mandatory")
    @Email(message = "Invalid email format")
    private String to;

    @NotBlank(message = "Subject is mandatory")
    @Size(max = 100, message = "Subject cannot be longer than 100 characters")
    private String subject;

    @NotBlank(message = "Body is mandatory")
    private String body;

    private MultipartFile file;

}
