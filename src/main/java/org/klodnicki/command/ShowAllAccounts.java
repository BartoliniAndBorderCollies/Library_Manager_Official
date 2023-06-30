package org.klodnicki.command;

import org.klodnicki.controller.AccountController;

public class ShowAllAccounts implements MenuCommand {

    public ShowAllAccounts(AccountController accountController) {
        this.accountController = accountController;
    }

    private final AccountController accountController;

    @Override
    public String getName() {
        return "show all accounts";
    }

    @Override
    public void execute() {
        accountController.showAllAccounts();
    }
}
