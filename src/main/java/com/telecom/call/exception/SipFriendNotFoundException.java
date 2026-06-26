package com.telecom.call.exception;

public class SipFriendNotFoundException extends RuntimeException {

    public SipFriendNotFoundException(String message) {
        super(message);
    }

    public SipFriendNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
