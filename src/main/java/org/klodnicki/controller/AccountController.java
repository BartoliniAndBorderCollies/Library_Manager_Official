package org.klodnicki.controller;

import org.klodnicki.service.AccountService;

public class AccountController {
    private final AccountService accountService = new AccountService();
    private final MenuController menuController;

    private static final String CREATE_INFO = "In order to create an account, please type the following information.";
    private static final String FIRST_NAME = "First name:";
    private static final String SECOND_NAME = "Second name:";
    private static final String LAST_NAME = "Last name:";
    private static final String PESEL = "Pesel (must be numbers only):";
    private static final String PHONE_NUMBER = "Phone number (must be numbers only):";
    private static final String EMAIL = "Email:";
    private static final String ADDRESS = "Address:";

    public AccountController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void create() {
        menuController.displayOnMenu(CREATE_INFO);
        String firstName = menuController.displayOnMenuAndAskForInput(FIRST_NAME);
        String secondName = menuController.displayOnMenuAndAskForInput(SECOND_NAME);
        String lastName = menuController.displayOnMenuAndAskForInput(LAST_NAME);
        int pesel = Integer.parseInt(menuController.displayOnMenuAndAskForInput(PESEL));
        int phoneNumber = Integer.parseInt(menuController.displayOnMenuAndAskForInput(PHONE_NUMBER));
        String email = menuController.displayOnMenuAndAskForInput(EMAIL);
        String address = menuController.displayOnMenuAndAskForInput(ADDRESS);

        accountService.create(firstName, secondName, lastName, pesel, phoneNumber, email, address);
    }
}
