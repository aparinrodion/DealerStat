package org.leverx.dealerstat.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    private final static String USER_ALREADY_EXISTS_PATTERN =
            "User with email %s already exists";

    public UserAlreadyExistsException(String email) {
        super(String.format(USER_ALREADY_EXISTS_PATTERN, email));
    }
}
