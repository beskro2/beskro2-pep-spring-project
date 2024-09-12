package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
AccountRepository accountRepository;

@Autowired
public AccountService(AccountRepository accountRepository){
    this.accountRepository = accountRepository;
}


//method to add a new account to db
public Account persistAccount(Account account){
    if(account.getPassword().length()>4 && 
    account.getUsername().length()>0 &&
    noDuplicatesCheck(account.getUsername())==null){
    return accountRepository.save(account);
    }else{
        return null;
    }
    
}

public Account noDuplicatesCheck(String accountUsername){
    return accountRepository.existanceCheck(accountUsername);
}


public Account getAccountByID(int userID){
   
   Optional<Account> optionalAccount = accountRepository.findById(userID);
   if(optionalAccount.isPresent()){
    Account returnAccount = optionalAccount.get();
    return returnAccount;
   }else{
    return null;
   }
}

public  Account verifyAccount(Account account){
   return accountRepository.verifyAccount(account.getUsername(), account.getPassword());
}



}
