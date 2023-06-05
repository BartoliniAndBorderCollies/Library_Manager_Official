package org.klodnicki.service;

import org.klodnicki.entity.Book;
import org.klodnicki.repository.BookRepository;

public class BookService {

    private final BookRepository bookRepository = new BookRepository();

    public void add(String title, String author, String isbn, String publisher, int publicationYear, int edition,
                    String genre, String description, String language, int copiesNumber) {
        bookRepository.add(new Book(title, author, isbn, publisher, publicationYear, edition, genre, description,
                language, copiesNumber));
    }
}
