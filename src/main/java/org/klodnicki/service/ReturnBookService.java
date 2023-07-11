package org.klodnicki.service;

import org.klodnicki.entity.Account;
import org.klodnicki.entity.BookInfo;
import org.klodnicki.entity.LendingInformation;
import org.klodnicki.exception.NotFoundInDatabaseException;

import java.time.LocalDateTime;
import java.util.List;

public class ReturnBookService {

    private final AccountService accountService;
    private final BookService bookService;
    private final LendingInformationService lendingInformationService;

    private static final int MAX_DAYS_LEND = 100;
    private static final int FINE_PER_DAY = 2;
    private static final String FINE_TO_BE_PAID = "Fine to be paid in $: ";

    public ReturnBookService(AccountService accountService, BookService bookService, LendingInformationService lendingInformationService) {
        this.accountService = accountService;
        this.bookService = bookService;
        this.lendingInformationService = lendingInformationService;
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

        LendingInformation lendingInformation = lendingInformationService.findLendingInformationByAccountAndBookInfo
                (account, bookInfo);


        bookInfo.removeAccount(account);
        int bookCopiesUpdate = bookInfo.getCopiesNumber() + 1;
        bookInfo.setCopiesNumber(bookCopiesUpdate);

        bookService.update(bookInfo);
    }

    private int countFineForKeepingBook(Long accountId, Long bookInfoId, LocalDateTime lendingDate) {
        accountId = lendingInformation.getAccountId();
        bookInfoId = lendingInformation.getBookInfoId();
        lendingDate = lendingInformation.getLendingDate();
        LocalDateTime returningDate = LocalDateTime.now();

        int daysOfYearWhenBookWasBorrowed = lendingDate.getDayOfYear();
        int daysOfYearTillNow = returningDate.getDayOfYear();

        int totalDays = daysOfYearTillNow - daysOfYearWhenBookWasBorrowed;
        int fine = 0;

        if (totalDays > MAX_DAYS_LEND) {
            fine = (totalDays - MAX_DAYS_LEND) * FINE_PER_DAY;
        }
        return fine;
    }
}
