package org.klodnicki.controller;

import jakarta.persistence.NoResultException;
import org.klodnicki.service.AccountService;
import org.klodnicki.service.BookService;
import org.klodnicki.service.LendBookService;

public class LendBookController {

    private final MenuController menuController;

    private final LendBookService lendBookService = new LendBookService(new AccountService(), new BookService());

    public LendBookController(MenuController menuController) {
        this.menuController = menuController;
    }

    private static final String LEND_BOOK_PROCEDURE = "In order to lend a book please fill the following information. " +
            "\nRemember that each reader can have maximum 10 books borrowed at the same time.";
    private static final String READER_INFORMATION = "Reader infomation:";
    private static final String FIRST_NAME = "First name:";
    private static final String LAST_NAME = "Last name:";
    private static final String PESEL = "Pesel:";
    private static final String BOOK_INFORMATION = "Book information:";
    private static final String BOOK_TITLE = "Title of a book:";
    private static final String AUTHOR = "Author:";
    private static final String ABORT_OPERATION = "An operation has been canceled.";
    private static final String LEND_BOOK_SUCCESS = "Success! The book has been successfully lent.";
    private static final String NOT_FOUND = "No result found.";


    public void lend() throws IllegalArgumentException, NoResultException {
        menuController.displayOnMenu(LEND_BOOK_PROCEDURE);
        menuController.displayOnMenu(READER_INFORMATION);
        String firstName = menuController.displayOnMenuAndAskForInput(FIRST_NAME);
        String lastName = menuController.displayOnMenuAndAskForInput(LAST_NAME);
        String pesel = menuController.displayOnMenuAndAskForInput(PESEL);

        menuController.displayOnMenu(BOOK_INFORMATION);
        String title = menuController.displayOnMenuAndAskForInput(BOOK_TITLE);
        String author = menuController.displayOnMenuAndAskForInput(AUTHOR);

        try {
            lendBookService.lend(firstName, lastName, pesel, title, author);
        } catch (IllegalArgumentException e) {
            menuController.displayOnMenu(e.getMessage());
            menuController.displayOnMenu(ABORT_OPERATION);
            return;
        } catch (NoResultException e) {
            menuController.displayOnMenu(NOT_FOUND);
            menuController.displayOnMenu(ABORT_OPERATION);
            return;
        }

        menuController.displayOnMenu(LEND_BOOK_SUCCESS);
    }
}
