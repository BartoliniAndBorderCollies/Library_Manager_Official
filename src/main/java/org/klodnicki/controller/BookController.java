package org.klodnicki.controller;

import org.klodnicki.exception.NotFoundInDatabaseException;
import org.klodnicki.service.BookService;

public class BookController {

    private final MenuController menuController;
    private final BookService bookService = new BookService();
    private static final String ADD_INFO = "In order to add book please provide necessary information.";
    private static final String TITLE = "Title:";
    private static final String AUTHOR = "Author:";
    private static final String ISBN = "ISBN:";
    private static final String PUBLISHER = "Publisher:";
    private static final String PUBLICATION_YEAR = "Year of publication (must be a number):";
    private static final String EDITION = "Edition (must be a number):";
    private static final String GENRE = "Genre";
    private static final String DESCRIPTION = "Description:";
    private static final String LANGUAGE = "Language:";
    private static final String COPIES_NUMBER = "Number of copies (must be a number):";
    private static final String ABORT_OPERATION = "An operation has been canceled.";
    private static final String SUCCESS_BOOK_ADDED = "A book has been successfully added.";
    private static final String MUST_BE_NUMBER = "Year, edition and copies must be numbers.";
    private static final String LIST_OF_BOOKS_IN_DATABASE = "This is a list of all books in the library:";
    private static final String SORTING_QUESTION = "Do you want to sort the results? Type yes or no:";
    private static final String UNKNOWN_COMMAND = "Unknown command";
    private static final String WHICH_FIELD_TO_SORT = "Which field do you want to sort?";

    public BookController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void add() throws IllegalArgumentException {
        int publicationYear;
        String edition;
        int copiesNumber;
        menuController.displayOnMenu(ADD_INFO);
        String title = menuController.displayOnMenuAndAskForInput(TITLE);
        String author = menuController.displayOnMenuAndAskForInput(AUTHOR);
        String isbn = menuController.displayOnMenuAndAskForInput(ISBN);

        try {
            publicationYear = Integer.parseInt(menuController.displayOnMenuAndAskForInput(PUBLICATION_YEAR));
            edition = menuController.displayOnMenuAndAskForInput(EDITION);
            copiesNumber = Integer.parseInt(menuController.displayOnMenuAndAskForInput(COPIES_NUMBER));
        } catch (NumberFormatException e) {
            menuController.displayOnMenu(MUST_BE_NUMBER);
            menuController.displayOnMenu(ABORT_OPERATION);
            return;
        }

        String publisher = menuController.displayOnMenuAndAskForInput(PUBLISHER);
        String genre = menuController.displayOnMenuAndAskForInput(GENRE);
        String description = menuController.displayOnMenuAndAskForInput(DESCRIPTION);
        String language = menuController.displayOnMenuAndAskForInput(LANGUAGE);

        try {
            bookService.add(title, author, isbn, publisher, publicationYear, edition, genre, description, language,
                    copiesNumber);
        } catch (IllegalArgumentException e) {
            menuController.displayOnMenu(e.getMessage());
            menuController.displayOnMenu(ABORT_OPERATION);
            return;
        }
        menuController.displayOnMenu(SUCCESS_BOOK_ADDED);
    }

    public void showBooks() {
        menuController.displayOnMenu(LIST_OF_BOOKS_IN_DATABASE);

        try {
            menuController.displayOnMenu(bookService.prepareListOfAllBooks());
        } catch (NotFoundInDatabaseException e) {
            menuController.displayOnMenu(e.getMessage());
        }

        String sortAnswer = menuController.displayOnMenuAndAskForInput(SORTING_QUESTION);
        if (sortAnswer.equals("no")) {
            return;
        }
        if (sortAnswer.equals("yes")) {
            String fieldToSort = menuController.displayOnMenuAndAskForInput(WHICH_FIELD_TO_SORT);

            try {
                switch (fieldToSort) {

                    case "title" -> menuController.displayOnMenu(bookService.prepareListOfAllBooksSortByTitle());
                }

            } catch (NotFoundInDatabaseException e) {
                menuController.displayOnMenu(e.getMessage());
            }


        } else {
            menuController.displayOnMenu(UNKNOWN_COMMAND);
        }

    }
}
