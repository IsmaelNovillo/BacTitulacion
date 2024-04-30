package com.dev.BackFenixc.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
