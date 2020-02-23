package com.assignment.product.exception;

public final class CategoryNotFoundException extends RuntimeException {

    private String id;

    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFoundException(final String message) {
        super(message);
    }

    public CategoryNotFoundException(String id, final String message) {
        super(message);
        this.id = id;
    }

    public CategoryNotFoundException(final Throwable cause) {
        super(cause);
    }
}
