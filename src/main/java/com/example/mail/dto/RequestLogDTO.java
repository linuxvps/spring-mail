package com.example.mail.dto;

public record RequestLogDTO(
        String id,
        String requestMethod,
        String requestUri,
        String requestBody,
        Integer responseStatus,
        String responseBody
) {}