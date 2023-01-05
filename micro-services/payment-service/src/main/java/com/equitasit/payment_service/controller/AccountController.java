package com.equitasit.payment_service.controller;

import com.equitasit.payment_service.dto.AccountDTO;
import com.equitasit.payment_service.dto.TransactionDTO;
import com.equitasit.payment_service.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity save(@RequestBody AccountDTO accountDTO) {

        log.debug("enter");
        log.info("saving the account info {}", accountDTO);
        AccountDTO savedAccountDTO = accountService.save(accountDTO);
        log.info("saved account info {}", savedAccountDTO);
        log.debug("exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccountDTO);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody AccountDTO accountDTO) {
        log.debug("enter");
        log.info("updating the account info {}", accountDTO);
        AccountDTO savedAccountDTO = accountService.update(accountDTO);
        log.info("updated account info {}", savedAccountDTO);
        log.debug("exit");
        return ResponseEntity.ok(savedAccountDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") Integer actId) {
        log.debug("enter");
        log.info("getting user info for act id {}", actId);
        AccountDTO accountDTO = accountService.getAccount(actId);
        log.info("account info {}", accountDTO);
        log.debug("exit");
        return ResponseEntity.ok(accountDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Integer actId) {

        log.debug("enter");
        log.info("removing act info for id {}", actId);

        accountService.remove(actId);

        log.info("getting account info for id {}", actId);
        log.debug("exit");
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity get() {
        log.debug("enter");

        List<AccountDTO> accountDTOList = accountService.getAllAccounts();

        log.info("accounts size {}", accountDTOList.size());
        log.debug("exit");
        return ResponseEntity.ok(accountDTOList);
    }


    @PostMapping("debit")
    public ResponseEntity debit(@RequestBody TransactionDTO transactionDTO) {

        log.debug("enter");
        log.info("saving the transactionDTO info {}", transactionDTO);
        accountService.debit(transactionDTO);

        log.debug("exit");
        return ResponseEntity.ok().build();
    }

    @PostMapping("credit")
    public ResponseEntity credit(@RequestBody TransactionDTO transactionDTO) {

        log.debug("enter");
        log.info("saving the transactionDTO info {}", transactionDTO);
        accountService.credit(transactionDTO);

        log.debug("exit");
        return ResponseEntity.ok().build();
    }


}
