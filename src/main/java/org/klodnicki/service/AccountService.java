package org.klodnicki.service;

import org.klodnicki.repository.AccountRepository;
import org.klodnicki.entity.Account;

public class AccountService {
    private final AccountRepository accountRepository = new AccountRepository();

    public void create(String firstName, String secondName, String lastName, String pesel, String phoneNumber,
                       String email, String address) {

            if (pesel.length() != 11) {
                throw new IllegalArgumentException("Pesel must have 11 digits.");

            }
            if (phoneNumber.equals("") && email.equals("")){
                throw new IllegalArgumentException("Phone number or email address must have a value.");
            }

        accountRepository.create(new Account(firstName, secondName, lastName, pesel, phoneNumber, email, address));
    }

    public Account findAccountByFirstNameAndLastNameAndPesel(String firstName, String lastName, String pesel) {
        return accountRepository.findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);
    }
}
