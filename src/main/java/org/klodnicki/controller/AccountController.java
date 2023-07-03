package org.klodnicki.controller;

import org.klodnicki.exception.NotFoundInDatabaseException;
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
    private static final String LIST_OF_ACCOUNTS = "Below you will find list of all accounts in database:";
    private static final String REMOVE_WARNING = "WARNING!!! You are going to remove an account. " +
            "Be aware that this action cannot be rollback. ";
    private static final String REMOVE_ACCOUNT = "Enter pesel of the account which you want to delete.";
    private static final String ASK_FOR_CONFIRMATION = "Are you sure you want to delete this account? Enter yes or no";
    private static final String SUCCESS_ACCOUNT_REMOVED = "Success! An account has been removed!";
    private static final String MODIFY_ACCOUNT_INFO = "You are about to modify an account.";
    private static final String LIST_PRESENTATION = "This is the list of results:";
    private static final String CHOOSE_ACCOUNT_TO_MODIFY = "Choose account to modify. Enter the account's pesel ";
    private static final String WHICH_FIELD_TO_MODIFY = "What is going to be modified? Enter a parameter:";
    private static final String ENTER_NEW_DATA = "Enter the new data:";
    private static final String SUCCESS_ACCOUNT_MODIFICATION = "Success! An account has been modified!";
    private static final String HOW_TO_SORT = "Which field you want to use to sort the results? " +
            "Enter for example \"pesel\"";
    private static final String SEARCH_FIRST_NAME = "Enter the first name of an account which you want to search:";
    private static final String SEARCH_LAST_NAME = "Enter the last name:";
    private static final String SORTING_QUESTION = "Do you want to sort the results? Type yes/no";
    private static final String SEE_BOOKS_OF_ACCOUNT_QUESTION = "Do you want to see the books borrowed by the account? " +
            "Type yes/no";
    private static final String ACCOUNT_PESEL_TO_SEE_BOOKS = "Enter pesel of an account:";

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

    public void showAllAccounts() {
        menuController.displayOnMenu(LIST_OF_ACCOUNTS);
        try {
            menuController.displayOnMenu(accountService.prepareListOfAllAccounts());
        } catch (NotFoundInDatabaseException e) {
            menuController.displayOnMenu(e.getMessage());
        }
    }

    public void searchAccount() {

        String searchFirstName = menuController.displayOnMenuAndAskForInput(SEARCH_FIRST_NAME);
        String searchLastName = menuController.displayOnMenuAndAskForInput(SEARCH_LAST_NAME);

        try {
            menuController.displayOnMenu(LIST_PRESENTATION);
            menuController.displayOnMenu(accountService.prepareListOfAccountsByFirstNameAndLastName
                    (searchFirstName, searchLastName));
        } catch (NotFoundInDatabaseException e) {
            menuController.displayOnMenu(e.getMessage());
        }

        String sortingResponse = menuController.displayOnMenuAndAskForInput(SORTING_QUESTION);
        if (!sortingResponse.equalsIgnoreCase("yes")) {
            showListOfBooksOfAccount(searchFirstName, searchLastName);
            return;
        }

        menuController.displayOnMenu(accountService.sortOptionNames());
        String sortParameter = menuController.displayOnMenuAndAskForInput(HOW_TO_SORT);
        menuController.displayOnMenu(accountService.prepareListOfAccountsByFirstNameAndLastNameAndParameter
                (searchFirstName, searchLastName, sortParameter));

        showListOfBooksOfAccount(searchFirstName, searchLastName);
    }

    private void showListOfBooksOfAccount(String firstName, String lastName) {
        String showBooksResponse = menuController.displayOnMenuAndAskForInput(SEE_BOOKS_OF_ACCOUNT_QUESTION);
        if (!showBooksResponse.equalsIgnoreCase("yes")) {
            menuController.displayOnMenu(ABORT_OPERATION);
            return;
        }

        String peselAccount = menuController.displayOnMenuAndAskForInput(ACCOUNT_PESEL_TO_SEE_BOOKS);
        try {
            menuController.displayOnMenu(accountService.prepareAccountByPeselWithBooks(firstName, lastName, peselAccount));
        } catch (NotFoundInDatabaseException e) {
            menuController.displayOnMenu(e.getMessage());
        }
    }

    public void removeAccount() {
        menuController.displayOnMenu(REMOVE_WARNING);
        showAllAccounts();
        String pesel = menuController.displayOnMenuAndAskForInput(REMOVE_ACCOUNT);
        String responseConfirmation = menuController.displayOnMenuAndAskForInput(ASK_FOR_CONFIRMATION);

        if (!responseConfirmation.equalsIgnoreCase("yes")) {
            menuController.displayOnMenu(ABORT_OPERATION);
            return;
        }

        try {
            accountService.removeAccount(pesel);
        } catch (NotFoundInDatabaseException e) {
            menuController.displayOnMenu(e.getMessage());
        }
        menuController.displayOnMenu(SUCCESS_ACCOUNT_REMOVED);
    }

    public void modifyAccount() {
        menuController.displayOnMenu(MODIFY_ACCOUNT_INFO);
        showAllAccounts();
        String peselAccountToModify = menuController.displayOnMenuAndAskForInput(CHOOSE_ACCOUNT_TO_MODIFY);
        menuController.displayOnMenu(accountService.sortOptionNames());
        String parameterToModify = menuController.displayOnMenuAndAskForInput(WHICH_FIELD_TO_MODIFY);
        String newData = menuController.displayOnMenuAndAskForInput(ENTER_NEW_DATA);

        try {
            accountService.modifyAccount(peselAccountToModify, parameterToModify, newData);
        } catch (IllegalArgumentException | NotFoundInDatabaseException e) {
            menuController.displayOnMenu(e.getMessage());
            menuController.displayOnMenu(ABORT_OPERATION);
            return;
        }
        menuController.displayOnMenu(SUCCESS_ACCOUNT_MODIFICATION);
    }
}
