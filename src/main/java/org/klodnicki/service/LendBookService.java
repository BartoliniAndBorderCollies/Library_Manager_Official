package org.klodnicki.service;

import org.klodnicki.entity.Account;
import org.klodnicki.entity.BookInfo;
import org.klodnicki.exception.MaximumBookBorrowedLimitException;
import org.klodnicki.exception.NotEnoughBookCopiesException;
import java.util.List;

public class LendBookService {

    private final AccountService accountService;
    private final BookService bookService;
    public static final int LENT_BOOK_LIMIT = 10;

    public LendBookService(AccountService accountService, BookService bookService) {
        this.accountService = accountService;
        this.bookService = bookService;
    }

    public void lendWithoutEdition(String firstName, String lastName, String pesel, String title, String author) throws
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

    public boolean ifHasMoreThanOneEdition(String title, String author) {
        List<BookInfo> listOfTheSameBooks = bookService.findBooksByTitleAndAuthorReturnList(title, author);

        for (int i = 0; i < listOfTheSameBooks.size(); i++) {
            for (int j = 0; j < listOfTheSameBooks.size(); j++) {
                if (listOfTheSameBooks.get(i).getEdition().equals(listOfTheSameBooks.get(j).getEdition())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<BookInfo> findBooksByTitleAndAuthorReturnList(String title, String author) {
        return bookService.findBooksByTitleAndAuthorReturnList(title, author);
    }

    public void lendWithEdition(String firstName, String lastName, String pesel, String title, String author, String edition) throws
            NotEnoughBookCopiesException, MaximumBookBorrowedLimitException {

        BookInfo bookInfo = bookService.findBookByTitleAndAuthorAndEdition(title, author, edition);
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
}
