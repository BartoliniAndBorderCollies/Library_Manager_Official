package org.klodnicki.exception;

public class MaximumBookBorrowedLimitException extends Exception{

    private static final String MESSAGE = "Maximum limit is reached. The reader cannot borrow more than %d books.";

    public MaximumBookBorrowedLimitException(int lentBookLimit) {
        super(String.format(MESSAGE, lentBookLimit));
    }
}
