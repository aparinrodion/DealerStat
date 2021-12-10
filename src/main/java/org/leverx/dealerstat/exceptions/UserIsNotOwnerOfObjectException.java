package org.leverx.dealerstat.exceptions;

public class UserIsNotOwnerOfObjectException extends DealerStatException {
    private static final String USER_IS_NOT_OWNER_EXCEPTION = "User %s is not owner of game object %d";

    public UserIsNotOwnerOfObjectException(String email, Integer gameObjectId) {
        super(String.format(USER_IS_NOT_OWNER_EXCEPTION, email, gameObjectId));
    }
}
