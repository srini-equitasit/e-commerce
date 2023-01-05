package com.equitasit.payment_service.controller;

import com.equitasit.payment_service.dto.AccountLogDTO;
import com.equitasit.payment_service.service.AccountLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("account/logs")
public class AccountLogController {

    @Autowired
    private AccountLogService accountLogService;

    @GetMapping
    public List<AccountLogDTO> getAll() {
        return accountLogService.getAccountLogs();
    }
}
