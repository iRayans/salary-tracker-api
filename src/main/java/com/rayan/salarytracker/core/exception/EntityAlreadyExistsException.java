package com.rayan.salarytracker.core.exception;

/**
 * Thrown when an entity already exists (typically during creation)
 */
public class EntityAlreadyExistsException extends GenericException {

    private static final String DEFAULT_CODE = "ENTITY_ALREADY_EXISTS";

    public EntityAlreadyExistsException(String message) {
        super(DEFAULT_CODE, message);
    }

    public EntityAlreadyExistsException(String code, String message) {
        super(code, message);
    }
}