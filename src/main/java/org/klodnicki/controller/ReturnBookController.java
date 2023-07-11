package org.klodnicki.controller;

import org.klodnicki.exception.NotFoundInDatabaseException;
import org.klodnicki.service.AccountService;
import org.klodnicki.service.BookService;
import org.klodnicki.service.LendingInformationService;
import org.klodnicki.service.ReturnBookService;

public class ReturnBookController {

    private final MenuController menuController;
    private final ReturnBookService returnBookService = new ReturnBookService(new AccountService(), new BookService(),
            new LendingInformationService());
    private static final String RETURN_PROCEDURE = "You are about to start a return book procedure. " +
            "\nFollow the instructions.";
    private static final String ACCOUNT_FIRST_NAME = "Enter the first name of a reader";
    private static final String ACCOUNT_LAST_NAME = "Enter last name of a reader:";
    private static final String ACCOUNT_PESEL = "Enter reader pesel";
    private static final String BORROWED_BOOKS = "A list of books borrowed by this reader.";
    private static final String BOOK_TITLE = "Enter a title of book which is going to be returned:";
    private static final String BOOK_AUTHOR = "Enter the author of the book.";
    private static final String EDITION = "Enter the edition of the book";
    private static final String ABORT_OPERATION = "Operation has been canceled";
    private static final String RETURN_BOOK_SUCCESS = "Success! The book has been returned!";

    public ReturnBookController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void returnBook() {

        menuController.displayOnMenu(RETURN_PROCEDURE);
        String firstName = menuController.displayOnMenuAndAskForInput(ACCOUNT_FIRST_NAME);
        String lastName = menuController.displayOnMenuAndAskForInput(ACCOUNT_LAST_NAME);
        String pesel = menuController.displayOnMenuAndAskForInput(ACCOUNT_PESEL);

        try {
            menuController.displayOnMenu(returnBookService.prepareListOfBorrowedBooksByAccount(firstName, lastName, pesel));
        } catch (NotFoundInDatabaseException e) {
            menuController.displayOnMenu(e.getMessage());
            return;
        }
        menuController.displayOnMenu(BORROWED_BOOKS);

        String title = menuController.displayOnMenuAndAskForInput(BOOK_TITLE);
        String author = menuController.displayOnMenuAndAskForInput(BOOK_AUTHOR);
        String edition = menuController.displayOnMenuAndAskForInput(EDITION);

        try {
            returnBookService.returnBook(firstName, lastName, pesel, title, author, edition);
        } catch (NotFoundInDatabaseException e) {
            menuController.displayOnMenu(e.getMessage());
            menuController.displayOnMenu(ABORT_OPERATION);
            return;
        }
        menuController.displayOnMenu(RETURN_BOOK_SUCCESS);
    }
}
