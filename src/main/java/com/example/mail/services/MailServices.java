package com.example.mail.services;

import com.example.mail.entity.Mail;
import com.example.mail.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServices {

    @Autowired
    private MailRepository mailRepository;


    public Mail saveMail(Mail mail) {
        return mailRepository.save(mail);
    }

    
    public List<Mail> fetchMailList() {
        return mailRepository.findAll();
    }

    
    public Mail updateMail(Mail mail, Long mailId) {
        return mailRepository.save(mail);
    }

    
    public void deleteMailById(Long mailId) {
        mailRepository.deleteById(mailId);
    }
}
