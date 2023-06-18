package org.klodnicki.controller;

import org.klodnicki.service.AccountService;
import org.klodnicki.service.BookService;
import org.klodnicki.service.ReturnBookService;

public class ReturnBookController {

    private final MenuController menuController;
    private final ReturnBookService returnBookService = new ReturnBookService(new AccountService(), new BookService());
    private static final String RETURN_PROCEDURE = "You are about to start a return book procedure. " +
            "\nFollow the instructions.";
    private static final String ACCOUNT_FIRST_NAME = "Enter the first name of a reader";
    private static final String ACCOUNT_LAST_NAME = "Enter last name of a reader:";
    private static final String ACCOUNT_PESEL = "Enter reader pesel";
    private static final String BORROWED_BOOKS = "A list of books borrowed by this reader.";
    private static final String BOOK_TITLE = "Enter a title of book which is going to be returned:";
    private static final String BOOK_AUTHOR = "Enter the author of the book.";
    private static final String EDITION = "Enter the edition of the book";

    public ReturnBookController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void returnBook() {

        menuController.displayOnMenu(RETURN_PROCEDURE);
        String firstName = menuController.displayOnMenuAndAskForInput(ACCOUNT_FIRST_NAME);
        String lastName = menuController.displayOnMenuAndAskForInput(ACCOUNT_LAST_NAME);
        String pesel = menuController.displayOnMenuAndAskForInput(ACCOUNT_PESEL);

        menuController.displayOnMenu(BORROWED_BOOKS);
        menuController.displayOnMenu(returnBookService.prepareListOfBorrowedBooksByAccount(firstName, lastName, pesel));

        menuController.displayOnMenuAndAskForInput(BOOK_TITLE);
        menuController.displayOnMenuAndAskForInput(BOOK_AUTHOR);
        menuController.displayOnMenuAndAskForInput(EDITION);


    }
}
