package com.littlesekii.emailservice.controller;

import com.littlesekii.emailservice.application.EmailSenderService;
import com.littlesekii.emailservice.core.EmailSenderUseCase;
import com.littlesekii.emailservice.core.dto.EmailRequestDTO;
import com.littlesekii.emailservice.core.exceptions.EmailServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {

    private final EmailSenderUseCase emailSenderUseCase;

    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderUseCase = emailSenderService;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO req) {
        try {
            this.emailSenderUseCase.sendEmail(req.to(), req.subject(), req.body());
            return ResponseEntity.ok().body("Email send successfully");
        } catch (EmailServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending email.");
        }
    }

}
