package com.equitasit.notification_service.repository;

import com.equitasit.notification_service.entity.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Integer> {
}
