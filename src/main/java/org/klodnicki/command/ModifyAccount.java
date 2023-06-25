package org.klodnicki.command;

import org.klodnicki.controller.AccountController;
import org.klodnicki.controller.MenuController;

public class ModifyAccount implements MenuCommand {

    public ModifyAccount(AccountController accountController) {
        this.accountController = accountController;
    }

    private final AccountController accountController;


    @Override
    public String getName() {
        return "modify account";
    }

    @Override
    public void execute() {
        accountController.modifyAccount();
    }
}
