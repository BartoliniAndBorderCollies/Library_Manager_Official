package org.klodnicki.command;

import org.klodnicki.controller.AccountController;

public class ShowAccounts implements MenuCommand {

    private final AccountController accountController;

    public ShowAccounts(AccountController accountController) {
        this.accountController = accountController;
    }

    @Override
    public String getName() {
        return "show accounts";
    }

    @Override
    public void execute() {
        accountController.showAccounts();

    }
}
