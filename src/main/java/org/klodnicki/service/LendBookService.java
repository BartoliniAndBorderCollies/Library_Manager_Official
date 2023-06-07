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

        // nie mialem pola account a chcialem zastosowac metode addBook, pole nie moze byc wiec account uzyskalem
        //poprzez ponizszy find

        Account account = findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);
        Book book = findBookByTitleAndAuthor(title, author);

        account.addBook(book);
        bookService.update(book);

        //wypożyczyć tzn przypisać daną książkę do danego konta, czyli dodać ją do listy?
        // ilość kopii to ilość dostępnych egzemplarzy?
        // przypisanie książki do konta zmniejszą o jeden ilość dostępnych kopii

    }

    public Account findAccountByFirstNameAndLastNameAndPesel(String firstName, String lastName, String pesel) {
        return accountService.findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);
    }

    public Book findBookByTitleAndAuthor(String title, String author) {
        return bookService.findBookByTitleAndAuthor(title, author);
    }
}
