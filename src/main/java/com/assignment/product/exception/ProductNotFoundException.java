package com.assignment.product.exception;

import org.springframework.data.domain.PageImpl;

public final class ProductNotFoundException extends RuntimeException {

    private String id;

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(final String message) {
        super(message);
    }

    public ProductNotFoundException(String id, final String message) {
        super(message);
        this.id = id;
    }

    public ProductNotFoundException(final Throwable cause) {
        super(cause);
    }
}
