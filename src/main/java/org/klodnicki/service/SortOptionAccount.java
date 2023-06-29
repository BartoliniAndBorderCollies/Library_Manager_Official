package org.klodnicki.service;

public enum SortOptionAccount {

    FIRST_NAME("first name"),
    SECOND_NAME("second name"),
    LAST_NAME("last name"),
    PESEL("pesel"),
    PHONE_NUMBER("phone number"),
    EMAIL("email"),
    ADDRESS("address");

    private final String sortName;

    SortOptionAccount(String sortName) {
        this.sortName = sortName;
    }

    public String getSortName() {
        return sortName;
    }
}
