package com.equitasit.notification_service.controller;

import com.equitasit.notification_service.dto.NotificationLogDTO;
import com.equitasit.notification_service.service.NotificationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("notification/log")
public class NotificationLogController {

    @Autowired
    private NotificationLogService notificationLogService;

    @GetMapping
    public ResponseEntity getAll() {

        List<NotificationLogDTO> notificationLogDTOList = notificationLogService.getAll();

        return ResponseEntity.ok(notificationLogDTOList);
    }

}
