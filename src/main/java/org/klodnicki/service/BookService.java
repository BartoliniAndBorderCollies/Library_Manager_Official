package org.klodnicki.service;

import org.klodnicki.entity.BookInfo;
import org.klodnicki.repository.BookRepository;

import java.util.List;

public class BookService {

    private final BookRepository bookRepository = new BookRepository();

    public void add(String title, String author, String isbn, String publisher, int publicationYear, String edition,
                    String genre, String description, String language, int copiesNumber) {

        if(title.equals("") || author.equals("")) {
            throw new IllegalArgumentException("Title and author must have a value.");
        }

        bookRepository.add(new BookInfo(title, author, isbn, publisher, publicationYear, edition, genre, description,
                language, copiesNumber));
    }

    public BookInfo findBookByTitleAndAuthor(String title, String author) {
        return bookRepository.findBookByTitleAndAuthor(title, author);
    }

    public BookInfo findBookByTitleAndAuthorAndEdition(String title, String author, String edition) {
        return bookRepository.findBookByTitleAndAuthorAndEdition(title, author, edition);
    }

    public void update(BookInfo bookInfo) {
        bookRepository.update(bookInfo);
    }

    public List<BookInfo> findBooksByTitleAndAuthorReturnList (String title, String author) {
        return bookRepository.findBooksByTitleAndAuthorReturnList(title, author);
    }
}
