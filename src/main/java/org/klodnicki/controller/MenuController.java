package org.klodnicki.controller;

import org.klodnicki.service.MenuService;
import org.klodnicki.view.MenuView;

import java.util.Scanner;

public class MenuController {
    private static final String WELCOME = """
            Welcome to Library Manager application.
                        
            Type a command. For help type "help"
            """;

    private static final String USER_COMMAND = "Command:";

    private final MenuView menuView = new MenuView();
    private final MenuService menuService = new MenuService();

    public void run() {
        menuService.addMainMenuCommands(this);
        menuView.update(WELCOME);

        String userInput = askForInput(USER_COMMAND);
        menuService.executeCommand(userInput);

        // wynik operacji wyżej
        menuView.update("");
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
}