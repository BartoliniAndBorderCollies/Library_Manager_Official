package org.klodnicki.service;

import org.klodnicki.exception.NotFoundInDatabaseException;
import org.klodnicki.repository.AccountRepository;
import org.klodnicki.entity.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private final AccountRepository accountRepository = new AccountRepository();

    public void create(String firstName, String secondName, String lastName, String pesel, String phoneNumber,
                       String email, String address) {

        if (pesel.length() != 11) {
            throw new IllegalArgumentException("Pesel must have 11 digits.");

        }
        if (phoneNumber.equals("") && email.equals("")) {
            throw new IllegalArgumentException("Phone number or email address must have a value.");
        }

        accountRepository.create(new Account(firstName, secondName, lastName, pesel, phoneNumber, email, address));
    }

    public Account findAccountByFirstNameAndLastNameAndPesel(String firstName, String lastName, String pesel) {
        return accountRepository.findAccountByFirstNameAndLastNameAndPesel(firstName, lastName, pesel);
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

    public void modifyAccount(String peselAccountToModify, String parameterToModify, String newData)
            throws IllegalArgumentException, NotFoundInDatabaseException {

        if (accountNotExist(peselAccountToModify)) {
            throw new NotFoundInDatabaseException(Account.class);
        }

        if (parameterToModify.equalsIgnoreCase(SortOptionAccount.PESEL.getSortName()) && newData.length() != 11) {
            throw new IllegalArgumentException("Pesel must have 11 digits.");

        }

        accountRepository.mergeAccount(peselAccountToModify, prepareParameterToDatabase(parameterToModify), newData);
    }

    private String prepareParameterToDatabase(String parameter) {

        for (SortOptionAccount sortOption : SortOptionAccount.values()) {
            if (sortOption.getSortName().equalsIgnoreCase(parameter)) {
                parameter = sortOption.getHqlParameter();
            }
        }
        return parameter;
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

    public List<String> prepareAllAccountsOrderByParameter(String parameter) {
        List<Account> accountsOrderByParameter = accountRepository.findAllAccountsOrderByParameter
                (prepareParameterToDatabase(parameter));
        List<String> results = new ArrayList<>();

        for (int i = 0; i < accountsOrderByParameter.size(); i++) {
            results.add(accountsOrderByParameter.get(i).toString());
        }
        return results;
    }

    public List<String> prepareListOfAccountsByFirstNameAndLastNameAndParameter
            (String firstName, String lastName, String parameter) {
        List<Account> accountsByFirstNameAndLastNameAndParameter = accountRepository.
                findAccountsByFirstNameAndLastNameAndParameter(firstName, lastName, prepareParameterToDatabase(parameter));
        List<String> results = new ArrayList<>();

        for (int i = 0; i < accountsByFirstNameAndLastNameAndParameter.size(); i++) {
            results.add(accountsByFirstNameAndLastNameAndParameter.get(i).toString());
        }
        return results;
    }

    private boolean peselExist(String pesel, String firstName, String lastName) {
        List<Account> accountList = accountRepository.findAccountsByFirstNameAndLastName(firstName, lastName);

        for (int i = 0; i < accountList.size(); i++) {
            if (pesel.equalsIgnoreCase(accountList.get(i).getPesel())) {
                return true;
            }
        }
        return false;
    }

    public List<String> prepareAccountByPeselWithBooks(String firstName, String lastName, String peselAccount)
            throws NotFoundInDatabaseException {
        List<String> results = new ArrayList<>();
        List<Account> preparedList = accountRepository.findAccountsByFirstNameAndLastName(firstName, lastName);

        for (int i = 0; i < preparedList.size(); i++) {
            if (peselExist(peselAccount, firstName, lastName)) {

                String fName = preparedList.get(i).getFirstName();
                String lName = preparedList.get(i).getLastName();
                String pesel = preparedList.get(i).getPesel();

                for (int j = 0; j < preparedList.get(i).getBooks().size(); j++) {
                    String title = preparedList.get(i).getBooks().get(j).getTitle();
                    String author = preparedList.get(i).getBooks().get(j).getAuthor();
                    String edition = preparedList.get(i).getBooks().get(j).getEdition();

                    results.add(SortOptionAccount.FIRST_NAME.getSortName() + ": " + fName);
                    results.add(SortOptionAccount.LAST_NAME.getSortName() + ": " + lName);
                    results.add(SortOptionAccount.PESEL.getSortName() + ": " + pesel);
                    results.add(SortOptionBookInfo.TITLE.getSortName() + ": " + title);
                    results.add(SortOptionBookInfo.AUTHOR.getSortName() + ": " + author);
                    results.add(SortOptionBookInfo.EDITION.getSortName() + ": " + edition);
                    results.add("-----------------------------------------------------------");
                }
            }
        }
        return results;
    }
}
