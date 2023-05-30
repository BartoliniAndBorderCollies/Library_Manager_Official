package org.klodnicki.service;

import org.klodnicki.command.CreateAccount;
import org.klodnicki.controller.AccountController;
import org.klodnicki.controller.MenuController;
import org.klodnicki.model.Menu;

public class MenuService {
    private final Menu menu = new Menu();

    public void addMainMenuCommands(MenuController menuController) {
        menu.addCommand(new CreateAccount(new AccountController(menuController)));
    }
}
