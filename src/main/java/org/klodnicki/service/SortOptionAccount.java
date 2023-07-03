package org.klodnicki.service;

public enum SortOptionAccount {

    FIRST_NAME("first name", "firstName"),
    SECOND_NAME("second name", "secondName"),
    LAST_NAME("last name", "lastName"),
    PESEL("pesel", "pesel"),
    PHONE_NUMBER("phone number", "phoneNumber"),
    EMAIL("email", "email"),
    ADDRESS("address", "address");

    private final String sortName;
    private final String hqlParameter;

    SortOptionAccount(String sortName, String hqlParameter) {
        this.sortName = sortName;
        this.hqlParameter = hqlParameter;
    }

    public String getSortName() {
        return sortName;
    }

    public String getHqlParameter() {
        return hqlParameter;
    }
}
