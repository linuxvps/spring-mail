package com.example.mail.controller;

import com.example.mail.dto.RequestLogDTO;
import com.example.mail.model.RequestLog;
import com.example.mail.service.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RequestLogController {

    @Autowired
    private RequestLogService requestLogService;

    @GetMapping("/request-logs")
    public List<RequestLogDTO> findAllRequestLogs() {
        List<RequestLog> allRequestLogs = requestLogService.findAllRequestLogs();
        List<RequestLogDTO> requestLogDTOs = new ArrayList<>();

        for (RequestLog requestLog : allRequestLogs) {
            RequestLogDTO requestLogDTO = new RequestLogDTO(
                    requestLog.getId(),
                    requestLog.getMethod(),
                    requestLog.getUri(),
                    requestLog.getRequestBody(),
                    requestLog.getStatusCode(),
                    requestLog.getResponseBody()
            );
            requestLogDTOs.add(requestLogDTO);
        }

        return requestLogDTOs;
    }

    @GetMapping("/request-logs/by-method")
    public List<RequestLog> findRequestLogsByMethod(@RequestParam String method) {
        return requestLogService.findRequestLogsByMethod(method);
    }

    @GetMapping("/request-logs/by-uri")
    public List<RequestLog> findRequestLogsByUri(@RequestParam String uri) {
        return requestLogService.findRequestLogsByUri(uri);
    }

    @GetMapping("/request-logs/by-status-code")
    public List<RequestLog> findRequestLogsByStatusCode(@RequestParam int statusCode) {
        return requestLogService.findRequestLogsByStatusCode(statusCode);
    }
}
