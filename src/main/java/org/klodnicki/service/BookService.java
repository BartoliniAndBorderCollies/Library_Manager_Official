package org.klodnicki.service;

import org.klodnicki.entity.Account;
import org.klodnicki.entity.BookInfo;
import org.klodnicki.exception.NotFoundInDatabaseException;
import org.klodnicki.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private final BookRepository bookRepository = new BookRepository();

    public void add(String title, String author, String isbn, String publisher, int publicationYear, String edition,
                    String genre, String description, String language, int copiesNumber) {

        if (title.equals("") || author.equals("")) {
            throw new IllegalArgumentException("Title and author must have a value.");
        }

        bookRepository.add(new BookInfo(title, author, isbn, publisher, publicationYear, edition, genre, description,
                language, copiesNumber));
    }

    public BookInfo findBookByTitleAndAuthor(String title, String author) throws NotFoundInDatabaseException {
        return bookRepository.findBookByTitleAndAuthor(title, author).orElseThrow(() ->
                new NotFoundInDatabaseException(BookInfo.class));
    }

    public BookInfo findBookByTitleAndAuthorAndEdition(String title, String author, String edition) throws
            NotFoundInDatabaseException {
        return bookRepository.findBookByTitleAndAuthorAndEdition(title, author, edition).orElseThrow(() ->
                new NotFoundInDatabaseException(BookInfo.class));
    }

    public void update(BookInfo bookInfo) {
        bookRepository.update(bookInfo);
    }

    public List<BookInfo> findBooksByTitleAndAuthor(String title, String author) {
        return bookRepository.findBooksByTitleAndAuthor(title, author);
    }

    public List<String> showBooks() throws NotFoundInDatabaseException {
        return prepareListOfAllBooks();
    }

    private List<String> prepareListOfAllBooks() throws NotFoundInDatabaseException {
        List<BookInfo> allBooksInDatabase = getAllBooksFromDatabase();
        List<String> results = new ArrayList<>();

        if (allBooksInDatabase.isEmpty()) {
            throw new NotFoundInDatabaseException(BookInfo.class);
        }

        for (int i = 0; i < allBooksInDatabase.size(); i++) {
            results.add(allBooksInDatabase.get(i).toString());
        }
        return results;
    }

    private List<BookInfo> getAllBooksFromDatabase() {
        return bookRepository.findAllBooks();
    }

    private List<BookInfo> getBooksInfoFromDatabaseByAccount(Account account) {
        return bookRepository.findBooksByAccount(account);
    }

    public List<String> prepareListOfBorrowedBooksByAccount(Account account) {
        List<String> results = new ArrayList<>();
        List<BookInfo> records = getBooksInfoFromDatabaseByAccount(account);
        for(int i = 0; i< records.size(); i++) {
            results.add(records.get(i).toString());
        }
        return results;
    }

    public List<String> prepareListOfBooks(String title, String author) {
        List<String> results = new ArrayList<>();
        List<BookInfo> records = getBooksInfoFromDatabaseByTitleAndAuthor(title, author);
        for (int i = 0; i < records.size(); i++) {
            results.add(records.get(i).toString());
        }
        return results;
    }

    private List<BookInfo> getBooksInfoFromDatabaseByTitleAndAuthor(String title, String author) {
        return findBooksByTitleAndAuthor(title, author);
    }
}
