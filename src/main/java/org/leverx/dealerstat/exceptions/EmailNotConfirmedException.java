package org.leverx.dealerstat.exceptions;

public class EmailNotConfirmedException extends RuntimeException {
    private static final String EMAIL_NOT_CONFIRMED_EXCEPTION_PATTERN =
            "Email %s is not confirmed";

    public EmailNotConfirmedException(String email) {
        super(String.format(EMAIL_NOT_CONFIRMED_EXCEPTION_PATTERN, email));
    }
}
