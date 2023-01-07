package com.equitasit.notification_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationLogDTO {

    private Integer id;


    private String fromEmail;


    private String msg;


    private Integer userId;
}
