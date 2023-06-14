package org.klodnicki.controller;

import org.klodnicki.service.MenuService;
import org.klodnicki.view.MenuView;

import java.util.List;
import java.util.Scanner;

public class MenuController {
    private static final String WELCOME = """
            Welcome to Library Manager application.
                        
            Type a command. For help type "help"
            """;
    private static final String USER_COMMAND = "Command:";
    private static final String ABORT_OPERATION = "Operation has been canceled. Not found such command.";

    private final MenuView menuView = new MenuView();
    private final MenuService menuService = new MenuService();

    public void run() {
        menuService.addMainMenuCommands(this);
        menuView.update(WELCOME);

        String userInput = askForInput(USER_COMMAND);
        if(!menuService.executeCommand(userInput)){
            menuView.update(ABORT_OPERATION);
        }
    }

    public String displayOnMenuAndAskForInput(String message) {
        return askForInput(message);
    }

    private String askForInput(String message) {
        Scanner scanner = new Scanner(System.in);

        displayOnMenu(message);
        return scanner.nextLine();
    }

    public void displayOnMenu(String message) {
        menuView.update(message);
    }

    public void displayOnMenu(List<String> messages) {
            menuView.update(messages);
    }
}