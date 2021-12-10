package org.leverx.dealerstat.exceptions;

public class UserWithEmailAlreadyExistsException extends DealerStatException {
    private final static String USER_ALREADY_EXISTS_PATTERN =
            "User with email %s already exists";

    public UserWithEmailAlreadyExistsException(String email) {
        super(String.format(USER_ALREADY_EXISTS_PATTERN, email));
    }
}
