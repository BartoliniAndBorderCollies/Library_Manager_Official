package org.klodnicki.service;

import org.klodnicki.command.*;
import org.klodnicki.controller.*;
import org.klodnicki.model.Menu;

import java.util.List;

public class MenuService {

    private final Menu menu = new Menu();

    public void addMainMenuCommands(MenuController menuController) { // tu jest taki parametr bo AccountController go potrzebuje
        menu.addCommand(new CreateAccount(new AccountController(menuController)));
        menu.addCommand(new AddBook(new BookController(menuController)));
        menu.addCommand(new LendBook(new LendBookController(menuController)));
        menu.addCommand(new ShowBooks(new BookController(menuController)));
        menu.addCommand(new ReturnBook(new ReturnBookController(menuController)));
        menu.addCommand(new SearchAccount(new AccountController(menuController)));
        menu.addCommand(new RemoveAccount(new AccountController(menuController)));
        menu.addCommand(new ModifyAccount(new AccountController(menuController)));
        menu.addCommand(new ShowAllAccounts(new AccountController(menuController)));
        menu.addCommand(new SearchBook(new BookController(menuController)));
    }

    public boolean executeCommand(String userInput) {
        List<MenuCommand> commands = menu.getCommands();
        for (MenuCommand command : commands) {
            if (userInput.equalsIgnoreCase(command.getName())) {
                menu.executeCommand(command);
                return true;
            }
        }
        return false;
    }
}
