package com.rayan.salarytracker.core.exception;

/**
 * Thrown when an entity cannot be found by its identifier
 */
public class EntityNotFoundException extends GenericException {

    private static final String DEFAULT_CODE = "ENTITY_NOT_FOUND";


    public EntityNotFoundException(String message) {
        super(DEFAULT_CODE, message);
    }


    public EntityNotFoundException(String code, String message) {
        super(code, message);
    }

    /**
     * Creates a new EntityNotFoundException for a specific entity type and ID
     */
    public static EntityNotFoundException forEntity(String entityName, Long id) {
        return new EntityNotFoundException(
                String.format("%s with ID %d not found", entityName, id)
        );
    }
}