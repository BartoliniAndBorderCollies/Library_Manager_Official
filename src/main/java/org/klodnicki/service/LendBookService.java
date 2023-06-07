package org.klodnicki.service;

import org.klodnicki.entity.Account;
import org.klodnicki.entity.Book;

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

    public Book findBookByTitleAndAuthor(String title, String author) {
        return bookService.findBookByTitleAndAuthor(title, author);
    }
}
