package org.klodnicki.command;

import org.klodnicki.controller.BookController;

public class AddBook implements MenuCommand {

    private final BookController bookController;

    public AddBook(BookController bookController) {
        this.bookController = bookController;
    }

    @Override
    public String getName() {
        return "add book";
    }

    @Override
    public void execute() {
        bookController.add();
    }
}
