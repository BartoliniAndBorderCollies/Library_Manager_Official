package org.klodnicki.command;

import org.klodnicki.controller.AccountController;

public class CreateAccount implements MenuCommand {

    private final AccountController accountController;

    public CreateAccount(AccountController accountController) {
        this.accountController = accountController;
    }

    @Override
    public String getName() {
        return "create account";
    }

    @Override
    public void execute() {
        accountController.create();
    }
}
