package org.klodnicki.exception;

public class MaximumBookBorrowedLimitException extends Exception{

    private static final String MESSAGE = "Maximum limit is reached. The reader cannot borrow more than 10 books.";

    public MaximumBookBorrowedLimitException() {
        super(MESSAGE);
    }
}
