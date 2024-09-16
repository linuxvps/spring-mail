package com.example.mail.service;

import com.example.mail.model.RequestLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RequestLogService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<RequestLog> findAllRequestLogs() {
        return mongoTemplate.findAll(RequestLog.class);
    }

    public List<RequestLog> findRequestLogsByMethod(String method) {
        Query query = new Query(Criteria.where("method").is(method));
        return mongoTemplate.find(query, RequestLog.class);
    }

    public List<RequestLog> findRequestLogsByUri(String uri) {
        Query query = new Query(Criteria.where("uri").is(uri));
        return mongoTemplate.find(query, RequestLog.class);
    }

    public List<RequestLog> findRequestLogsByStatusCode(int statusCode) {
        Query query = new Query(Criteria.where("statusCode").is(statusCode));
        return mongoTemplate.find(query, RequestLog.class);
    }
}