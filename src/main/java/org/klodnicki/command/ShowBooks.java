package org.klodnicki.command;

import org.klodnicki.controller.BookController;

public class ShowBooks implements MenuCommand{

    private final BookController bookController;

    public ShowBooks(BookController bookController) {
        this.bookController = bookController;
    }

    @Override
    public String getName() {
        return "show books";
    }

    @Override
    public void execute() {
        bookController.showBooks();
    }
}
