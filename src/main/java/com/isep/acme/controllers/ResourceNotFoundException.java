package com.isep.acme.controllers;

import java.util.UUID;

import com.isep.acme.model.User;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(final String string) {
        super(string);
    }

    public ResourceNotFoundException(final Class<?> clazz, final long id) {
        super(String.format("Entity %s with id %d not found", clazz.getSimpleName(), id));
    }

    public ResourceNotFoundException(Class<User> class1, UUID userId) {
    }
}
