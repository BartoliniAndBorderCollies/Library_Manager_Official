package org.klodnicki.service;

import org.klodnicki.entity.BookInfo;
import org.klodnicki.exception.NotFoundInDatabaseException;
import org.klodnicki.exception.SortParameterNotFoundException;
import org.klodnicki.repository.AccountRepository;
import org.klodnicki.entity.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private final AccountRepository accountRepository = new AccountRepository();

    private static final String PESEL_MIN_DIGITS = "Pesel must have 11 digits";
    private static final String PHONE_AND_EMAIL_MUST_HAVE_VALUE = "Phone number or email address must have a value.";

    public void create(String firstName, String secondName, String lastName, String pesel, String phoneNumber,
                       String email, String address) {

        if (pesel.length() != 11) {
            throw new IllegalArgumentException(PESEL_MIN_DIGITS);

        }
        if (phoneNumber.equals("") && email.equals("")) {
            throw new IllegalArgumentException(PHONE_AND_EMAIL_MUST_HAVE_VALUE);
        }

        accountRepository.create(new Account(firstName, secondName, lastName, pesel, phoneNumber, email, address));
    }

    public Account findAccountByFirstNameAndLastNameAndPesel(String firstName, String lastName, String pesel)
            throws NotFoundInDatabaseException {
        return accountRepository.findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel).
                orElseThrow(() -> new NotFoundInDatabaseException(Account.class));
    }

    public List<String> prepareListOfAllAccounts() throws NotFoundInDatabaseException {
        List<Account> allAccountsFromDatabase = getAllAccountsFromDatabase();
        List<String> results = new ArrayList<>();

        if (allAccountsFromDatabase.isEmpty()) {
            throw new NotFoundInDatabaseException(Account.class);
        }

        for (int i = 0; i < allAccountsFromDatabase.size(); i++) {
            results.add(allAccountsFromDatabase.get(i).toString());
        }
        return results;
    }

    private List<Account> getAllAccountsFromDatabase() {
        return accountRepository.findAllAccounts();
    }

    public void removeAccount(String pesel) throws NotFoundInDatabaseException {
        if (accountNotExist(pesel)) {
            throw new NotFoundInDatabaseException(Account.class);
        }
        accountRepository.removeAccount(pesel);
    }

    private boolean accountNotExist(String pesel) {
        return accountRepository.findAccountByPesel(pesel).isEmpty();
    }

    public void modifyAccount(String pesel, String parameterToModify, String newInput)
            throws IllegalArgumentException, NotFoundInDatabaseException, SortParameterNotFoundException {

        if (accountNotExist(pesel)) {
            throw new NotFoundInDatabaseException(Account.class);
        }

        if (parameterToModify.equalsIgnoreCase(SortOptionAccount.PESEL.getSortName()) && newInput.length() != 11) {
            throw new IllegalArgumentException(PESEL_MIN_DIGITS);

        }

        accountRepository.mergeAccount(pesel, prepareParameterToDatabase(parameterToModify), newInput);
    }

    private String prepareParameterToDatabase(String parameter) throws SortParameterNotFoundException {

        for (SortOptionAccount sortOption : SortOptionAccount.values()) {
            if (sortOption.getSortName().equalsIgnoreCase(parameter)) {
                return sortOption.getHqlParameter();
            }
        }
        throw new SortParameterNotFoundException();
    }

    public List<String> sortOptionNames() {
        List<String> namesList = new ArrayList<>();
        for (SortOptionAccount sortOption : SortOptionAccount.values()) {
            namesList.add(sortOption.getSortName());
        }
        return namesList;
    }

    public List<String> prepareListOfAccountsByFirstNameAndLastName(String firstName, String lastName)
            throws NotFoundInDatabaseException {
        List<Account> accountsByFirstNameAndLastName = accountRepository.findAccountsByFirstNameAndLastName
                (firstName, lastName);
        List<String> results = new ArrayList<>();

        if (accountsByFirstNameAndLastName.isEmpty()) {
            throw new NotFoundInDatabaseException(Account.class);
        }

        for (Account account : accountsByFirstNameAndLastName) {
            results.add(account.toString());
        }
        return results;
    }

    public List<String> prepareAllAccountsOrderByParameter(String parameter) throws SortParameterNotFoundException {
        List<Account> accountsOrderByParameter = accountRepository.findAllAccountsOrderByParameter
                (prepareParameterToDatabase(parameter));
        List<String> results = new ArrayList<>();

        for (int i = 0; i < accountsOrderByParameter.size(); i++) {
            results.add(accountsOrderByParameter.get(i).toString());
        }
        return results;
    }

    public List<String> prepareAccountByPeselWithBooks(String firstName, String lastName, String peselAccount)
            throws NotFoundInDatabaseException {

        List<String> results = new ArrayList<>();
        Account account = findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, peselAccount);
        List<BookInfo> books = account.getBooks();

        if (books.isEmpty()) {
            throw new NotFoundInDatabaseException(BookInfo.class);
        }

        results.add(SortOptionAccount.FIRST_NAME.getSortName() + ": " + firstName);
        results.add(SortOptionAccount.LAST_NAME.getSortName() + ": " + lastName);
        results.add(SortOptionAccount.PESEL.getSortName() + ": " + peselAccount);
        results.add("-----------------------------------------------------------");

        for (LendingInformation book : books) {
            String title = book.getBookInfo().getTitle();
            String author = book.getBookInfo().getAuthor();
            String edition = book.getBookInfo().getEdition();

            results.add(SortOptionBookInfo.TITLE.getSortName() + ": " + title);
            results.add(SortOptionBookInfo.AUTHOR.getSortName() + ": " + author);
            results.add(SortOptionBookInfo.EDITION.getSortName() + ": " + edition);
            results.add("-----------------------------------------------------------");
        }
        return results;
    }
}
