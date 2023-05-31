package org.klodnicki.service;

import org.klodnicki.repository.AccountRepository;
import org.klodnicki.entity.Account;
import org.klodnicki.view.MenuView;

public class AccountService {
    private final AccountRepository accountRepository = new AccountRepository();


    public void create(String firstName, String secondName, String lastName, String pesel, String phoneNumber, String email, String address) {
        if(phoneNumber == null && email == null) {
            System.out.println("Phone number or email address must have a value."); //TODO: sout shouldnt be here
            return;
        }
        accountRepository.create(new Account(firstName, secondName, lastName, pesel, phoneNumber, email, address));
    }
}
