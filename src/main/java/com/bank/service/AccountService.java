package com.bank.service;

import com.bank.dto.AccountDto;
import com.bank.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto updateAccount(AccountDto accountDto, Long id);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    List<AccountDto> findAllAccount();
    String delete(Long id);


}
