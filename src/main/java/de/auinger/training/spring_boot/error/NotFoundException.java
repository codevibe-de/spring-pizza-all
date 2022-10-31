package de.auinger.training.spring_boot.error;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entity, String id) {
        super(String.format( "Entity of type '%s' with id '%s' not found", entity, id));
    }
}
