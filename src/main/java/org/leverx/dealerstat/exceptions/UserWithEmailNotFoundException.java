package org.leverx.dealerstat.exceptions;

public class UserWithEmailNotFound extends RuntimeException {
    private final static String USER_WITH_EMAIL_NOT_FOUND_PATTERN =
            "User with email %s not found";

    public UserWithEmailNotFound(String email) {
        super(String.format(USER_WITH_EMAIL_NOT_FOUND_PATTERN, email));
    }
}
