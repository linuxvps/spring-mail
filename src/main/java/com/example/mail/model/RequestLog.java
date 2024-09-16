package com.example.mail.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "request_logs")
public class RequestLog {
    @Id
    private String id;
    private String method;
    private String uri;
    private String requestBody;
    private String responseBody;
    private int statusCode;
    private Date timestamp;

    // Getters and setters
}