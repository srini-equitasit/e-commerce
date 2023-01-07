package com.equitasit.notification_service.service;

import com.equitasit.notification_service.dto.EmailMsgDTO;
import com.equitasit.notification_service.entity.NotificationLog;
import com.equitasit.notification_service.repository.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private NotificationLogService notificationLogService;


    @Value("${fromEmail}")
    private String fromEmail;


    public void sendMessage(final SimpleMailMessage simpleMailMessage) {

        this.mailSender.send(simpleMailMessage);
    }

    public void sendMessage(final EmailMsgDTO emailMsgDTO) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText(emailMsgDTO.getMsg());
        simpleMailMessage.setSubject(emailMsgDTO.getSubject());
        simpleMailMessage.setCc(emailMsgDTO.getCc());
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(emailMsgDTO.getTo());


        this.mailSender.send(simpleMailMessage);
        notificationLogService.save(emailMsgDTO, fromEmail);
    }


}