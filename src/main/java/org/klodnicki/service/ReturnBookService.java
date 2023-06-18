package org.klodnicki.service;

import org.klodnicki.entity.Account;

import java.util.List;

public class ReturnBookService {

    private final AccountService accountService;

    private final BookService bookService;

    public ReturnBookService(AccountService accountService, BookService bookService) {
        this.accountService = accountService;
        this.bookService = bookService;
    }

    public List<String> prepareListOfBorrowedBooksByAccount(String  firstName, String lastName, String pesel) {
        Account account = accountService.findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);

        return bookService.prepareListOfBorrowedBooksByAccount(account);
    }




}
