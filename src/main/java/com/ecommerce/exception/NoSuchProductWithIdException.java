package com.ecommerce.exception;

import com.amazonaws.services.devicefarm.model.Run;

public class NoSuchProductWithIdException extends RuntimeException {

    public NoSuchProductWithIdException() {
        super();
    }

    public NoSuchProductWithIdException(String message) {
        super(message);
    }
}
