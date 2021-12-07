package org.leverx.dealerstat.exceptions;

public class UserIsNotOwnerOfComment extends RuntimeException {
    private static final String USER_IS_NOT_OWNER = "User with email %s is not owner of comment %d";

    public UserIsNotOwnerOfComment(String email, Integer id) {
        super(String.format(USER_IS_NOT_OWNER, email, id));
    }
}
