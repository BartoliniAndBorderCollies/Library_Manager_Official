package org.klodnicki.service;

public enum SortOptionBookInfo {
    TITLE("title", "title"),
    AUTHOR("author", "author"),
    ISBN("isbn", "isbn"),
    PUBLISHER("publisher", "publisher"),
    PUBLICATION_YEAR("publication year", "publicationYear"),
    EDITION("edition", "edition"),
    GENRE("genre", "genre"),
    DESCRIPTION("description", "description"),
    LANGUAGE("language", "language"),
    COPIES_NUMBER("copies number", "copiesNumber");


    private final String sortName;
    private final String hqlParameter;

    SortOptionBookInfo(String sortName, String hqlParameter) {
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
