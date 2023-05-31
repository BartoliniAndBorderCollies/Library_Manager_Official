package org.klodnicki.service;

import org.klodnicki.command.CreateAccount;
import org.klodnicki.controller.AccountController;
import org.klodnicki.controller.MenuController;
import org.klodnicki.model.Menu;

public class MenuService {

    private final Menu menu = new Menu();

    public void addMainMenuCommands(MenuController menuController) { // tu jest taki parametr bo AccountController go potrzebuje
        menu.addCommand(new CreateAccount(new AccountController(menuController)));
    }

    public void executeCommand(String userInput) {
        // TODO: walidacja userInput
        menu.executeCommand(userInput);
    }
}
