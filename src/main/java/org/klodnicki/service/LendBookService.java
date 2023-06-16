package org.klodnicki.service;

import org.klodnicki.entity.Account;
import org.klodnicki.entity.BookInfo;
import org.klodnicki.exception.MaximumBookBorrowedLimitException;
import org.klodnicki.exception.NotEnoughBookCopiesException;
import org.klodnicki.exception.NotFoundInDatabaseException;

import java.util.ArrayList;
import java.util.List;

public class LendBookService {

    private final AccountService accountService;
    private final BookService bookService;
    public static final int LENT_BOOK_LIMIT = 10;

    public LendBookService(AccountService accountService, BookService bookService) {
        this.accountService = accountService;
        this.bookService = bookService;
    }

    public void lend(String firstName, String lastName, String pesel, String title, String author, String edition)
            throws NotEnoughBookCopiesException, MaximumBookBorrowedLimitException, NotFoundInDatabaseException {

        BookInfo bookInfo;
        if (edition == null) {
            bookInfo = bookService.findBookByTitleAndAuthor(title, author);
        } else {
            bookInfo = bookService.findBookByTitleAndAuthorAndEdition(title, author, edition);
        }

        // All the validation
        if (bookInfo.getCopiesNumber() <= 0) {
            throw new NotEnoughBookCopiesException();
        }

        Account account = accountService.findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);
        if (account.getBooks().size() > LENT_BOOK_LIMIT) {
            throw new MaximumBookBorrowedLimitException(LENT_BOOK_LIMIT);
        }

        bookInfo.addAccount(account);

        int leftCopiesNumber = bookInfo.getCopiesNumber() - 1;
        bookInfo.setCopiesNumber(leftCopiesNumber);

        bookService.update(bookInfo);
    }

    public boolean hasMoreThanOneEdition(String title, String author) {
        return bookService.findBooksByTitleAndAuthor(title, author).size() > 1;
    }

    public List<BookInfo> findBooksByTitleAndAuthor(String title, String author) {
        return bookService.findBooksByTitleAndAuthor(title, author);
    }

    public List<String> prepareListOfBooks(String title, String author) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < findBooksByTitleAndAuthor(title, author).size(); i++) {
            results.add(findBooksByTitleAndAuthor(title, author).get(i).toString());
        }
        return results;
    }
}
