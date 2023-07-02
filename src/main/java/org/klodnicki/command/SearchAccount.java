package org.klodnicki.command;

import org.klodnicki.controller.AccountController;

public class SearchAccount implements MenuCommand {

    private final AccountController accountController;

    public SearchAccount(AccountController accountController) {
        this.accountController = accountController;
    }

    @Override
    public String getName() {
        return "search account";
    }

    @Override
    public void execute() {
        accountController.searchAccount();

    }
}
