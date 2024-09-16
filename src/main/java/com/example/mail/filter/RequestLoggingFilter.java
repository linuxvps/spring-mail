package com.example.mail.filter;

import com.example.mail.model.RequestLog;
import com.example.mail.repository.RequestLogRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Date;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Autowired
    private RequestLogRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        String requestBody = new String(requestWrapper.getContentAsByteArray());
        String responseBody = new String(responseWrapper.getContentAsByteArray());

        // Log the request and response
        logRequest(request, requestBody, response, responseBody);
    }
    private void logRequest(HttpServletRequest request, String requestBody, HttpServletResponse response, String responseBody) {
        // Create a new document to store in MongoDB
        RequestLog log = new RequestLog();
        log.setMethod(request.getMethod());
        log.setUri(request.getRequestURI());
        log.setRequestBody(requestBody);
        log.setResponseBody(responseBody);
        log.setStatusCode(response.getStatus());
        log.setTimestamp(new Date());

        // Save the document to MongoDB
        repository.save(log);
    }

}
