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

}
