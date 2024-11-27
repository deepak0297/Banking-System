package com.bank.service.impl;

import com.bank.dto.AccountDto;
import com.bank.entity.Account;
import com.bank.exception.AccountException;
import com.bank.mapper.AccountMapper;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto updateAccount(AccountDto accountDto, Long id) {
        return null;
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id).orElseThrow(()->
                new AccountException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id).orElseThrow(()->
                new AccountException("Account not Exist"));
       double total = account.getBalance() + amount;
       account.setBalance(total);
       Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account =accountRepository.findById(id).orElseThrow(
                ()-> new AccountException("not found")
        );
        if(account.getBalance() < amount){
            throw new AccountException("in sufficient balance");
        }
        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount =accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> findAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public String delete(Long id) {
        Account account =accountRepository.findById(id)
                .orElseThrow(()->new AccountException("account s not exist"));
        accountRepository.delete(account);
        return "account is deleted";
    }
}
