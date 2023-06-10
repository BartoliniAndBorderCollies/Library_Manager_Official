package org.klodnicki.service;

import org.klodnicki.entity.Account;
import org.klodnicki.entity.BookInfo;

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

        BookInfo bookInfo = findBookByTitleAndAuthor(title, author);
        if (bookInfo.getCopiesNumber() <= 0) {
            throw new IllegalArgumentException("This book is not available.");
        }

        Account account = findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);
        if (account.getBooks().size() > 10) {
            throw new IllegalArgumentException("Maximum limit is reached. The reader cannot borrow more than 10 books.");
        }

        bookInfo.addAccount(account); //lub account.addBook(book)

        int leftCopiesNumber = bookInfo.getCopiesNumber() - 1;
        bookInfo.setCopiesNumber(leftCopiesNumber);

        bookService.update(bookInfo); // lub accountService.update(account)
    }

    public Account findAccountByFirstNameAndLastNameAndPesel(String firstName, String lastName, String pesel) {
        return accountService.findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);
    }

    public BookInfo findBookByTitleAndAuthor(String title, String author) {
        return bookService.findBookByTitleAndAuthor(title, author);
    }
}
