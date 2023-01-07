package com.equitasit.notification_service.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "notification_log")
public class NotificationLog {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "from_email")
    private String fromEmail;

    @Lob
    @Column(name = "msg")
    private String msg;

    @Column(name = "user_id")
    private Integer userId;

}
