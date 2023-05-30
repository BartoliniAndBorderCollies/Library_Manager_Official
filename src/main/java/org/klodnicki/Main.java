package org.klodnicki;


import org.klodnicki.command.CreateAccount;
import org.klodnicki.controller.AccountController;
import org.klodnicki.controller.MenuController;
import org.klodnicki.database.AccountRepository;
import org.klodnicki.service.AccountService;
import org.klodnicki.service.CommandService;
import org.klodnicki.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        AccountController accountController = new AccountController(accountService, view);
        CommandService commandService = new CommandService();
        CreateAccount createAccount = new CreateAccount(accountController);
        MenuController menuController = new MenuController(view, createAccount, commandService);

        menuController.run();

    }
}
