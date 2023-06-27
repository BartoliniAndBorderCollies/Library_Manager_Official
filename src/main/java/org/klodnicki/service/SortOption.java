package org.klodnicki.service;

public enum SortOption {
    TITLE("title"),
    AUTHOR("author"),
    ISBN("isbn"),
    PUBLICATION_YEAR("publication year"),
    PUBLISHER("publisher"),
    EDITION("edition");

    private final String sortName;

    SortOption(String sortName) {
        this.sortName = sortName;
    }

    public String getSortName() {
        return sortName;
    }
}
