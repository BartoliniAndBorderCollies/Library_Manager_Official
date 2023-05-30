package org.klodnicki.controller;

import org.klodnicki.command.CreateAccount;
import org.klodnicki.service.CommandService;
import org.klodnicki.view.View;

import java.util.Scanner;

public class MenuController {
    private static final String WELCOME = """
            Welcome to Library Manager application.
                        
            Type a command. For help type "help"
            """;
    private static final String USER_COMMAND = "Command:";
    private static final String UNKNOWN_COMMAND = "Unknown command";
    private final View view;
    private final CreateAccount createAccount;
    private final CommandService commandService;


    public MenuController(View view, CreateAccount createAccount, CommandService commandService) {
        this.view = view;
        this.createAccount = createAccount;
        this.commandService = commandService;
    }

    public void run() {
        commandService.addCommand();
        view.update(WELCOME);
        String userInput = askForInput(USER_COMMAND);

        if (userInput.equalsIgnoreCase("create account")) {
            createAccount.execute();
            return;
        }
        if (userInput.equalsIgnoreCase("help")) {
            commandService.showCommands();
        } else {
            view.update(UNKNOWN_COMMAND);
        }
    }

    public String askForInput(String userInput) {
        Scanner scanner = new Scanner(System.in);

        view.update(userInput);
        return scanner.nextLine();
    }
}



