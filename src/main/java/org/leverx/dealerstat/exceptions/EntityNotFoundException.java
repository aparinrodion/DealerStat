package org.leverx.dealerstat.exceptions;


public class EntityNotFoundException extends DealerStatException {
    private final static String ENTITY_NOT_FOUND_PATTERN = "Entity %s with id %d not found";

    public EntityNotFoundException(Class<?> entityName, Integer entityId) {
        super(String.format(ENTITY_NOT_FOUND_PATTERN, entityName.getSimpleName(), entityId));
    }
}
