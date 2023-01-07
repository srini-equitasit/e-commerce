package com.equitasit.notification_service.controller;

import com.equitasit.notification_service.dto.EmailMsgDTO;
import com.equitasit.notification_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity send(@RequestBody EmailMsgDTO msg) {

        emailService.sendMessage(msg);

        return ResponseEntity.ok().build();
    }
}
