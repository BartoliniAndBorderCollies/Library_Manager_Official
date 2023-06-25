package org.klodnicki.command;

import org.klodnicki.controller.AccountController;

public class RemoveAccount implements MenuCommand {

    public RemoveAccount(AccountController accountController) {
        this.accountController = accountController;
    }

    private final AccountController accountController;

    @Override
    public String getName() {
        return "remove account";
    }

    @Override
    public void execute() {
        accountController.removeAccount();
    }
}
