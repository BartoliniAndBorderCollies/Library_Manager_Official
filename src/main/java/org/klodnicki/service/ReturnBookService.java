package org.klodnicki.service;

import org.klodnicki.entity.Account;
import org.klodnicki.entity.BookInfo;
import org.klodnicki.entity.LendingInformation;
import org.klodnicki.exception.NotFoundInDatabaseException;

import java.util.List;

public class ReturnBookService {

    private final AccountService accountService;

    private final BookService bookService;
    private final LendingInformation lendingInformation;

    public ReturnBookService(AccountService accountService, BookService bookService) {
        this.accountService = accountService;
        this.bookService = bookService;
        this.lendingInformation = lendingInformation;
    }

    public List<String> prepareListOfBorrowedBooksByAccount(String firstName, String lastName, String pesel)
            throws NotFoundInDatabaseException {
        Account account = accountService.findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);

        return bookService.prepareListOfBorrowedBooksByAccount(account);
    }

    public void returnBook(String firstName, String lastName, String pesel, String title, String author,
                           String edition) throws NotFoundInDatabaseException {
        Account account = accountService.findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);
        BookInfo bookInfo = bookService.findBookByTitleAndAuthorAndEdition(title, author, edition);

        bookInfo.removeAccount(account);
        int bookCopiesUpdate = bookInfo.getCopiesNumber() + 1;
        bookInfo.setCopiesNumber(bookCopiesUpdate);

        bookService.update(bookInfo);
    }


}
