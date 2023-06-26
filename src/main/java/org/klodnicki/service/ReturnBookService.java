package org.klodnicki.service;

import org.klodnicki.entity.Account;
import org.klodnicki.entity.BookInfo;
import org.klodnicki.exception.NotFoundInDatabaseException;

import java.time.LocalDateTime;
import java.util.List;

public class ReturnBookService {

    private final AccountService accountService;

    private final BookService bookService;

    private static final int MAX_DAYS_LEND = 100;
    private static final int FINE_PER_DAY = 2;

    public ReturnBookService(AccountService accountService, BookService bookService) {
        this.accountService = accountService;
        this.bookService = bookService;
    }

    public List<String> prepareListOfBorrowedBooksByAccount(String firstName, String lastName, String pesel) {
        Account account = accountService.findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);

        return bookService.prepareListOfBorrowedBooksByAccount(account);
    }

    public void returnBook(String firstName, String lastName, String pesel, String title, String author,
                           String edition) throws NotFoundInDatabaseException {
        Account account = accountService.findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);
        BookInfo bookInfo = bookService.findBookByTitleAndAuthorAndEdition(title, author, edition);

        if (countFineForKeepingBook(bookInfo) > 0) {
            System.out.println("Fine to be paid:" + countFineForKeepingBook(bookInfo) + " $");
        }
        bookInfo.setReturningDate(LocalDateTime.now());
        bookInfo.removeAccount(account);

        int bookCopiesUpdate = bookInfo.getCopiesNumber() + 1;
        bookInfo.setCopiesNumber(bookCopiesUpdate);


        bookService.update(bookInfo);
    }

    private int countFineForKeepingBook(BookInfo bookInfo) {
        int lendingDate = bookInfo.getLendingDate().getDayOfYear();
        int returningDate = LocalDateTime.now().getDayOfYear();
        int totalDays = returningDate - lendingDate;
        int fine = 0;

        if (totalDays > MAX_DAYS_LEND) {
            fine = (totalDays - MAX_DAYS_LEND) * FINE_PER_DAY;
        }
        return fine;
    }
}
