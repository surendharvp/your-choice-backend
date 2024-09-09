package com.backEnd.serviceMarketplace.service;

import com.backEnd.serviceMarketplace.model.Account;
import com.backEnd.serviceMarketplace.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.regex.Pattern.matches;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void register(Account account) {
        accountRepository.userSave(account);
    }

    public int login(String email, String password) {
        Account account = accountRepository.findByUsername(email);

        if(account!=null && matches(password,account.getPassword())){
            return 1;
        }
        return 0;
    }
}