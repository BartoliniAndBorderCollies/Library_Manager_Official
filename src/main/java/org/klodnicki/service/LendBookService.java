package org.klodnicki.service;

import org.klodnicki.entity.Account;

public class LendBookService {

    private final AccountService accountService;
    private final BookService bookService;

    public LendBookService(AccountService accountService, BookService bookService) {
        this.accountService = accountService;
        this.bookService = bookService;
    }

    public void lend(String firstName, String lastName, String pesel, String title, String author) {

    }

    public Account findAccountByFirstNameAndLastNameAndPesel(String firstName, String lastName, String pesel) {
        return accountService.findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);
    }
}
