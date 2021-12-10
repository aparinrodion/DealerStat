package org.leverx.dealerstat.exceptions;

public class UserWithEmailNotFoundException extends DealerStatException {
    private final static String USER_WITH_EMAIL_NOT_FOUND_PATTERN =
            "User with email %s not found";

    public UserWithEmailNotFoundException(String email) {
        super(String.format(USER_WITH_EMAIL_NOT_FOUND_PATTERN, email));
    }
}
