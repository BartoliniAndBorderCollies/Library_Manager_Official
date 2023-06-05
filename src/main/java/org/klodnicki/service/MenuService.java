package org.klodnicki.service;

import org.klodnicki.command.CreateAccount;
import org.klodnicki.command.MenuCommand;
import org.klodnicki.controller.AccountController;
import org.klodnicki.controller.MenuController;
import org.klodnicki.model.Menu;

import java.util.List;

public class MenuService {

    private final Menu menu = new Menu();

    public void addMainMenuCommands(MenuController menuController) { // tu jest taki parametr bo AccountController go potrzebuje
        menu.addCommand(new CreateAccount(new AccountController(menuController)));
    }

    public boolean executeCommand(String userInput) {
        List<MenuCommand> commands = menu.getCommands();
        for (MenuCommand command: commands) {
            if(userInput.equalsIgnoreCase(command.getName())) {
                menu.executeCommand(command);
                return true;
            }
        }
        return false;
    }
}
