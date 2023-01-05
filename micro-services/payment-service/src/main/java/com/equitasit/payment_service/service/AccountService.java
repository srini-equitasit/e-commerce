package com.equitasit.payment_service.service;

import com.equitasit.payment_service.dto.AccountDTO;
import com.equitasit.payment_service.dto.TransactionDTO;
import com.equitasit.payment_service.entity.Account;
import com.equitasit.payment_service.entity.AccountLog;
import com.equitasit.payment_service.exception.PaymentException;
import com.equitasit.payment_service.repository.AccountLogRepository;
import com.equitasit.payment_service.repository.AccountRepository;
import com.equitasit.payment_service.util.MsgConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountLogRepository accountLogRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public AccountDTO save(AccountDTO accountDTO) {
        Account saved = accountRepository.save(modelMapper.map(accountDTO, Account.class));
        return modelMapper.map(saved, AccountDTO.class);
    }

    @Transactional
    public AccountDTO update(AccountDTO accountDTO) {

        Optional<Account> existingAccount = accountRepository.findById(accountDTO.getId());
        if (!existingAccount.isPresent()) {
            throw new PaymentException(MsgConstants.ACT_NOT_FOUND);
        }

        Account saved = accountRepository.save(modelMapper.map(accountDTO, Account.class));
        return modelMapper.map(saved, AccountDTO.class);
    }

    public AccountDTO getAccount(Integer actId) {
        Optional<Account> existingAccount = accountRepository.findById(actId);
        if (!existingAccount.isPresent()) {
            throw new PaymentException(MsgConstants.ACT_NOT_FOUND);
        }
        return modelMapper.map(existingAccount.get(), AccountDTO.class);

    }

    @Transactional
    public void remove(Integer actId) {
        accountRepository.deleteById(actId);
    }

    public List<AccountDTO> getAllAccounts() {
        List<Account> accountList = accountRepository.findAll();
        return accountList.stream().map(act -> modelMapper.map(act, AccountDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public void debit(TransactionDTO transactionDTO) {

        Optional<Account> existingAccount = accountRepository.findById(transactionDTO.getActId());
        if (!existingAccount.isPresent()) {
            throw new PaymentException(MsgConstants.ACT_NOT_FOUND);
        }
        Account account = existingAccount.get();
        Double amount = account.getAmount() - transactionDTO.getAmount();

        if (amount < 0) {
            throw new PaymentException(MsgConstants.ACT_IN_SUFFICIENT_FUNDS);
        }

        AccountLog accountLog = createAccountLog(transactionDTO, account, "DEBIT");

        account.setAmount(amount);

        accountRepository.save(account);

        accountLogRepository.save(accountLog);

    }

    private AccountLog createAccountLog(TransactionDTO transactionDTO, Account account, String type) {
        AccountLog accountLog = new AccountLog();
        accountLog.setType(type);
        accountLog.setBalance(account.getAmount());
        accountLog.setAmount(transactionDTO.getAmount());
        accountLog.setUserId(transactionDTO.getUserId());
        return accountLog;
    }

    @Transactional
    public void credit(TransactionDTO transactionDTO) {

        Optional<Account> existingAccount = accountRepository.findById(transactionDTO.getActId());
        if (!existingAccount.isPresent()) {
            throw new PaymentException(MsgConstants.ACT_NOT_FOUND);
        }
        Account account = existingAccount.get();
        Double amount = account.getAmount() + transactionDTO.getAmount();

        AccountLog accountLog = createAccountLog(transactionDTO, account, "CREDIT");

        account.setAmount(amount);

        accountRepository.save(account);

        accountLogRepository.save(accountLog);

    }

}
