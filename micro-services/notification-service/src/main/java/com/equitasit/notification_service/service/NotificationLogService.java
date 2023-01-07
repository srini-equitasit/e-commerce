package com.equitasit.notification_service.service;

import com.equitasit.notification_service.dto.EmailMsgDTO;
import com.equitasit.notification_service.dto.NotificationLogDTO;
import com.equitasit.notification_service.entity.NotificationLog;
import com.equitasit.notification_service.repository.NotificationLogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class NotificationLogService {

    @Autowired
    private NotificationLogRepository notificationLogRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void save(final EmailMsgDTO emailMsgDTO, String fromEmail) {
        try {
            NotificationLog notificationLog = new NotificationLog();
            notificationLog.setMsg(mapper.writeValueAsString(emailMsgDTO));
            notificationLog.setFromEmail(fromEmail);
            notificationLog.setUserId(emailMsgDTO.getUserId());
            notificationLogRepository.save(notificationLog);
        } catch (Exception e) {
            log.error("error while logging the entry  ", e);
        }
    }

    public List<NotificationLogDTO> getAll() {
        List<NotificationLog> notificationLogs = notificationLogRepository.findAll();
        return notificationLogs.stream().map(notificationLog -> modelMapper.map(notificationLog, NotificationLogDTO.class)).collect(Collectors.toList());
    }

}
