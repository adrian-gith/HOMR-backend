package com.ecommerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
public class ApiException {

    private Integer code;
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public ApiException(Integer code, String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
