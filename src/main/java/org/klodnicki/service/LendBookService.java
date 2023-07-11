package org.klodnicki.service;

import org.klodnicki.entity.Account;
import org.klodnicki.entity.BookInfo;
import org.klodnicki.entity.LendingInformation;
import org.klodnicki.exception.MaximumBookBorrowedLimitException;
import org.klodnicki.exception.NotEnoughBookCopiesException;
import org.klodnicki.exception.NotFoundInDatabaseException;

import java.time.LocalDateTime;
import java.util.List;

public class LendBookService {

    private final AccountService accountService;
    private final BookService bookService;
    private final LendingInformationService lendingInformationService;

    public static final int LENT_BOOK_LIMIT = 10;

    public LendBookService(AccountService accountService, BookService bookService, LendingInformationService lendingInformationService) {
        this.accountService = accountService;
        this.bookService = bookService;
        this.lendingInformationService = lendingInformationService;
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
        if (account.getLendingInformationAboutAccountList().size() > LENT_BOOK_LIMIT) {
            throw new MaximumBookBorrowedLimitException(LENT_BOOK_LIMIT);
        }

        LendingInformation lendingInformation = new LendingInformation(bookInfo, account, LocalDateTime.now());

        int leftCopiesNumber = bookInfo.getCopiesNumber() - 1;
        bookInfo.setCopiesNumber(leftCopiesNumber);

        bookService.update(bookInfo);
        lendingInformationService.add(lendingInformation);
    }

    public boolean hasMoreThanOneEdition(String title, String author) {
        return bookService.findBooksByTitleAndAuthor(title, author).size() > 1;
    }

    public List<String> prepareListOfBooks(String title, String author) {
        return bookService.prepareListOfBooks(title, author);
    }
}
