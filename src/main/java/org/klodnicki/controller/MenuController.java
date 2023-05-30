package org.klodnicki.controller;

import org.klodnicki.service.MenuService;
import org.klodnicki.view.MenuView;

public class MenuController {
    private static final String WELCOME = """
            Welcome to Library Manager application.
                        
            Type a command. For help type "help"
            """;
    private final MenuView menuView = new MenuView();
    private final MenuService menuService = new MenuService();

    public void run() {
        menuService.addMainMenuCommands(this);
        menuView.update(WELCOME);
    }
}