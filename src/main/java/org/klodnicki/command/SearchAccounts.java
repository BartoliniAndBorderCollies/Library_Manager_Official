package org.klodnicki.command;

import org.klodnicki.controller.AccountController;

public class SearchAccounts implements MenuCommand {

    private final AccountController accountController;

    public SearchAccounts(AccountController accountController) {
        this.accountController = accountController;
    }

    @Override
    public String getName() {
        return "search accounts";
    }

    @Override
    public void execute() {
        accountController.searchAccounts();

    }
}
