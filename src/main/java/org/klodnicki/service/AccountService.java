package org.klodnicki.service;

import org.klodnicki.database.AccountRepository;
import org.klodnicki.entities.Account;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(Account account) {
        accountRepository.create(account);
        return account;
    }
}
