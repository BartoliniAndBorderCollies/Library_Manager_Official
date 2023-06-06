package org.klodnicki.command;

import org.klodnicki.controller.LendBookController;

public class LendBook implements MenuCommand{

    private final LendBookController lendBookController;

    public LendBook(LendBookController lendBookController) {
        this.lendBookController = lendBookController;
    }

    @Override
    public String getName() {
        return "lend book";
    }

    @Override
    public void execute() {
        lendBookController.lend();
    }
}
