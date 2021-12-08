package org.leverx.dealerstat.exceptions;

public class WrongPasswordException extends RuntimeException {
    private static final String WRONG_PASSWORD = "Password doesnt match with user %s";

    public WrongPasswordException(String email) {
        super(String.format(WRONG_PASSWORD, email));
    }
}
