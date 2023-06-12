package org.klodnicki.service;

import org.klodnicki.entity.Account;
import org.klodnicki.entity.BookInfo;
import org.klodnicki.exception.MaximumBookBorrowedLimitException;
import org.klodnicki.exception.NotEnoughBookCopiesException;

public class LendBookService {

    private final AccountService accountService;
    private final BookService bookService;
    private static final int LENT_BOOK_LIMIT = 10;

    public LendBookService(AccountService accountService, BookService bookService) {
        this.accountService = accountService;
        this.bookService = bookService;
    }

    public void lend(String firstName, String lastName, String pesel, String title, String author) throws
            NotEnoughBookCopiesException, MaximumBookBorrowedLimitException {

        // nie mialem pola account a chcialem zastosowac metode addBook, pole nie moze byc wiec account uzyskalem
        //poprzez ponizszy find

        BookInfo bookInfo = bookService.findBookByTitleAndAuthor(title, author);
        if (bookInfo.getCopiesNumber() <= 0) {
            throw new NotEnoughBookCopiesException();
        }

        Account account = accountService.findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);
        if (account.getBooks().size() > LENT_BOOK_LIMIT) {
            throw new MaximumBookBorrowedLimitException(LENT_BOOK_LIMIT);
        }

        bookInfo.addAccount(account); //lub account.addBook(book)

        int leftCopiesNumber = bookInfo.getCopiesNumber() - 1;
        bookInfo.setCopiesNumber(leftCopiesNumber);

        bookService.update(bookInfo); // lub accountService.update(account)
    }
}
