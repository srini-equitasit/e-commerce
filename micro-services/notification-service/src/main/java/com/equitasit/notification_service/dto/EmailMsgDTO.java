package com.equitasit.notification_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmailMsgDTO {
    private String msg;

    private String subject;

    private String[] cc;

    private String[] to;

    private Integer userId;
}
