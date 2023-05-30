package org.klodnicki.controller;

import org.klodnicki.entities.Account;
import org.klodnicki.service.AccountService;
import org.klodnicki.view.View;

import java.util.Scanner;

public class AccountController {
    private final AccountService accountService;
    private final View view;

    private static final String CREATE_INFO = "In order to create an account, please type the following information.";
    private static final String FIRST_NAME = "First name:";
    private static final String SECOND_NAME = "Second name:";
    private static final String LAST_NAME = "Last name:";
    private static final String PESEL = "Pesel (must be numbers only):";
    private static final String PHONE_NUMBER = "Phone number (must be numbers only):";
    private static final String EMAIL = "Email:";
    private static final String ADDRESS = "Address:";

    public AccountController(AccountService accountService, View view) {
        this.accountService = accountService;
        this.view = view;
    }

    public Account create(){
        Scanner scanner = new Scanner(System.in);
        view.update(CREATE_INFO);
        view.update(FIRST_NAME);
        String firstName = scanner.nextLine();
        view.update(SECOND_NAME);
        String secondName = scanner.nextLine();
        view.update(LAST_NAME);
        String lastName = scanner.nextLine();
        view.update(PESEL);
        int pesel = scanner.nextInt();
        view.update(PHONE_NUMBER);
        int phoneNumber = scanner.nextInt();
        scanner.nextLine();
        view.update(EMAIL);
        String email = scanner.nextLine();
        view.update(ADDRESS);
        String address = scanner.nextLine();

        return accountService.create(new Account(firstName, secondName, lastName, pesel, phoneNumber, email, address));
    }
}
