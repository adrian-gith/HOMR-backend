package com.ecommerce.exception;

public class NoParentCategoriesException extends RuntimeException{
    public NoParentCategoriesException(String message) {
        super(message);
    }

    public NoParentCategoriesException() {
    }
}
