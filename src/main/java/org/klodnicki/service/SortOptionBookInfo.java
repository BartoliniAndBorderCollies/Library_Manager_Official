package org.klodnicki.service;

public enum SortOptionBookInfo {
    TITLE("title"),
    AUTHOR("author"),
    ISBN("isbn"),
    PUBLISHER("publisher"),
    PUBLICATION_YEAR("publication year"),
    EDITION("edition"),
    GENRE("genre"),
    DESCRIPTION("description"),
    LANGUAGE("language"),
    COPIES_NUMBER("copies number");

    private final String sortName;

    SortOptionBookInfo(String sortName) {
        this.sortName = sortName;
    }

    public String getSortName() {
        return sortName;
    }
}