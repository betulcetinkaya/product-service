package com.assignment.product.exception;

public final class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(final String message) {
        super(message);
    }
}
