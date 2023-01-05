package com.equitasit.payment_service.repository;

import com.equitasit.payment_service.entity.AccountLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountLogRepository extends JpaRepository<AccountLog, Integer> {
}
