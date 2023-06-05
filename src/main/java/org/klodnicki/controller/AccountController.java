package org.klodnicki.controller;

import org.klodnicki.service.AccountService;

public class AccountController {
    private final AccountService accountService = new AccountService();
    private final MenuController menuController;

    private static final String CREATE_INFO = "In order to create an account, please type the following information.";
    private static final String FIRST_NAME = "First name:";
    private static final String SECOND_NAME = "Second name:";
    private static final String LAST_NAME = "Last name:";
    private static final String PESEL = "Pesel (must have 11 digits):";
    private static final String PHONE_NUMBER = "Phone number:";
    private static final String EMAIL = "Email:";
    private static final String ADDRESS = "Address:";
    private static final String ABORT_OPERATION = "An operation has been canceled.";
    private static final String SUCCESS_ACCOUNT_CREATION = "Success! An account has been created!";


    public AccountController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void create() throws IllegalArgumentException {

        menuController.displayOnMenu(CREATE_INFO);
        String firstName = menuController.displayOnMenuAndAskForInput(FIRST_NAME);
        String secondName = menuController.displayOnMenuAndAskForInput(SECOND_NAME);
        String lastName = menuController.displayOnMenuAndAskForInput(LAST_NAME);
        String pesel = menuController.displayOnMenuAndAskForInput(PESEL);
        String phoneNumber = menuController.displayOnMenuAndAskForInput(PHONE_NUMBER);
        String email = menuController.displayOnMenuAndAskForInput(EMAIL);
        String address = menuController.displayOnMenuAndAskForInput(ADDRESS);

        try {
            accountService.create(firstName, secondName, lastName, pesel, phoneNumber, email, address);
        } catch (IllegalArgumentException e) {
            menuController.displayOnMenu(e.getMessage());
            menuController.displayOnMenu(ABORT_OPERATION);
            return;
        }
        menuController.displayOnMenu(SUCCESS_ACCOUNT_CREATION);
    }
}
