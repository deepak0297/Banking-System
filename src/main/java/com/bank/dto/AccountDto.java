package com.bank.dto;

import lombok.*;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class AccountDto {
//    private Long id;
//    private String accountHolderName;
//    private double balance;
//
//}

public record AccountDto(Long id,
                         String accountHolderName,
                         double balance){

}