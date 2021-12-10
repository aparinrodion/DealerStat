package org.leverx.dealerstat.exceptions;

public class UserIsNotOwnerOfCommentException extends DealerStatException {
    private static final String USER_IS_NOT_OWNER = "User with email %s is not owner of comment %d";

    public UserIsNotOwnerOfCommentException(String email, Integer id) {
        super(String.format(USER_IS_NOT_OWNER, email, id));
    }
}
