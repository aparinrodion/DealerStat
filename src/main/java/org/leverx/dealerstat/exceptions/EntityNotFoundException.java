package org.leverx.dealerstat.exceptions;


public class EntityNotFoundException extends RuntimeException {
    private final static String ENTITY_NOT_FOUND_PATTERN = "Entity %s with id %d not found";

    public EntityNotFoundException(String entityName, Integer entityId) {
        super(String.format(ENTITY_NOT_FOUND_PATTERN, entityName, entityId));
    }
}
