package org.klodnicki.exception;

public class NotEnoughBookCopiesException extends Exception{

    private static final String MESSAGE = "This book is not available.";

    public NotEnoughBookCopiesException() {
        super(MESSAGE);
    }
}
