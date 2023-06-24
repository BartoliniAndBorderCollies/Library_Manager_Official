package org.klodnicki.exception;

public class SortParameterNotFoundException extends Exception {

    private static final String MESSAGE = "This sort parameter does not exist.";

    public SortParameterNotFoundException() {
        super(MESSAGE);
    }
}
