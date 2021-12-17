package com.uaic.lab7.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, Integer id) {
        super(String.format("%s with id %d not found", entity, id));
    }

    public EntityNotFoundException(String entity) {
        super(String.format("%s not found", entity));
    }
}

