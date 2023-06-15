package org.klodnicki.exception;

public class NotFoundInDatabaseException extends Exception {

    private static final String MESSAGE = "Object of class %s has not been found.";

    public NotFoundInDatabaseException(Class<?> clazz) {
        super(String.format(MESSAGE, clazz.getName()));
    }
}

