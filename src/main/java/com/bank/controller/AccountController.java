package com.bank.controller;

import com.bank.dto.AccountDto;
import com.bank.service.AccountService;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add Account

    @PostMapping("/add")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    // Get Account Details
    @GetMapping("/get/{id}")
    public HttpEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    // Deposit Amount
    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String, Double>request){
        AccountDto accountDto =accountService.deposit(id,request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }

    // Withdraw Amount
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String, Double>request){
        AccountDto accountDto =accountService.withdraw(id,request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }

    // Find All Accounts
    @GetMapping("/getall")
    public ResponseEntity<List<AccountDto>> findAll(){
        List<AccountDto> accounts = accountService.findAllAccount();
        return ResponseEntity.ok(accounts);
    }

    //Delete Account
    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
      String message =  accountService.delete(id);
      return ResponseEntity.ok(message);
    }
}
