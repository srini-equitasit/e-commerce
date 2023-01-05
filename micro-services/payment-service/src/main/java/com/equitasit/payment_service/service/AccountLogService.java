package com.equitasit.payment_service.service;

import com.equitasit.payment_service.dto.AccountLogDTO;
import com.equitasit.payment_service.entity.AccountLog;
import com.equitasit.payment_service.repository.AccountLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountLogService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountLogRepository accountLogRepository;

    public List<AccountLogDTO> getAccountLogs() {
        List<AccountLog> accountLogList = accountLogRepository.findAll();

        return accountLogList.stream().map(act -> modelMapper.map(act, AccountLogDTO.class)).collect(Collectors.toList());

    }
}
