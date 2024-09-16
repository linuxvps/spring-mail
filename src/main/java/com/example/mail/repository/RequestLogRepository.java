package com.example.mail.repository;

import com.example.mail.model.RequestLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestLogRepository extends MongoRepository<RequestLog, String> {
}
