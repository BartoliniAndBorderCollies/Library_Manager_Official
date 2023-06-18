package org.klodnicki.command;

import org.klodnicki.controller.ReturnBookController;

public class ReturnBook implements MenuCommand{

    private final ReturnBookController returnBookController;

    public ReturnBook(ReturnBookController returnBookController) {
        this.returnBookController = returnBookController;
    }

    @Override
    public String getName() {
        return "return book";
    }

    @Override
    public void execute() {
        returnBookController.returnBook();
    }
}
