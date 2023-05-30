package org.klodnicki.service;

import org.klodnicki.repository.AccountRepository;
import org.klodnicki.entity.Account;

public class AccountService {
    private final AccountRepository accountRepository = new AccountRepository();

    public void create(String firstName, String secondName, String lastName, int pesel, int phoneNumber, String email, String address) {
        // TODO: check if there is given all required fields, and one of the optional phone or email.
        accountRepository.create(new Account(firstName, secondName, lastName, pesel, phoneNumber, email, address));
    }
}
