package org.klodnicki.service;

import org.klodnicki.repository.AccountRepository;
import org.klodnicki.entity.Account;
import org.klodnicki.view.MenuView;

public class AccountService {
    private final AccountRepository accountRepository = new AccountRepository();


    public void create(String firstName, String secondName, String lastName, int pesel, int phoneNumber, String email, String address) {
        if(phoneNumber == 0 && email == null) {
            System.out.println("Phone number or email address must have a value."); //TODO: sout shouldnt be here
            return;
        }
        accountRepository.create(new Account(firstName, secondName, lastName, pesel, phoneNumber, email, address));
    }
}
