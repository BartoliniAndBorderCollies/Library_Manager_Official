package org.klodnicki.controller;

import org.klodnicki.service.AccountService;
import org.klodnicki.service.BookService;

public class BookController {

    private final MenuController menuController;
    private final BookService bookService = new BookService();
    private static final String ADD_INFO = "In order to add book please provide necessary information.";
    private static final String TITLE = "Title:";
    private static final String AUTHOR = "Author:";
    private static final String ISBN = "ISBN:";
    private static final String PUBLISHER = "Publisher:";
    private static final String PUBLICATION_YEAR = "Year of publication:";
    private static final String EDITION = "Edition:";
    private static final String GENRE = "Genre";
    private static final String DESCRIPTION = "Description:";
    private static final String LANGUAGE = "Language:";
    private static final String COPIES_NUMBER = "Number of copies:";
    private static final String ABORT_OPERATION = "An operation has been canceled.";
    private static final String SUCCESS_BOOK_ADDED = "A book has been successfully added.";

    public BookController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void add() {
        menuController.displayOnMenu(ADD_INFO);
        String title = menuController.displayOnMenuAndAskForInput(TITLE);
        String author = menuController.displayOnMenuAndAskForInput(AUTHOR);
        String isbn = menuController.displayOnMenuAndAskForInput(ISBN);
        String publisher = menuController.displayOnMenuAndAskForInput(PUBLISHER);
        int publicationYear = Integer.parseInt(menuController.displayOnMenuAndAskForInput(PUBLICATION_YEAR));
        int edition = Integer.parseInt(menuController.displayOnMenuAndAskForInput(EDITION));
        String genre = menuController.displayOnMenuAndAskForInput(GENRE);
        String description = menuController.displayOnMenuAndAskForInput(DESCRIPTION);
        String language = menuController.displayOnMenuAndAskForInput(LANGUAGE);
        int copiesNumber = Integer.parseInt(menuController.displayOnMenuAndAskForInput(COPIES_NUMBER));

        try {
            bookService.add(title, author, isbn, publisher, publicationYear, edition, genre, description, language,
                    copiesNumber);
        }catch (IllegalArgumentException e) {
            menuController.displayOnMenu(e.getMessage());
            menuController.displayOnMenu(ABORT_OPERATION);
            return;
        }
        menuController.displayOnMenu(SUCCESS_BOOK_ADDED);
    }
}
