package com.telecom.call.exception;

public class RolInvaledException extends RuntimeException {

    public RolInvaledException(String message) {
        super(message);
    }

    public RolInvaledException(String message, Throwable cause) {
        super(message, cause);
    }
}
