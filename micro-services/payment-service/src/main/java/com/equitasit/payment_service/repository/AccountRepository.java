package com.equitasit.payment_service.repository;

import com.equitasit.payment_service.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
