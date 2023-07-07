package org.klodnicki.command;

import org.klodnicki.controller.BookController;

public class SearchBook implements MenuCommand {

    public SearchBook(BookController bookController) {
        this.bookController = bookController;
    }

    private final BookController bookController;

    @Override
    public String getName() {
        return "search book";
    }

    @Override
    public void execute() {
        bookController.searchBook();
    }
}
