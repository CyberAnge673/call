package com.telecom.call.exception;

public class ExtensionNotFoundException extends RuntimeException {

    public ExtensionNotFoundException(String message) {
        super(message);
    }

    public ExtensionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
