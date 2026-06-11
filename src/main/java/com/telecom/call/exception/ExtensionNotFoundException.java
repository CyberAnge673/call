package com.telecom.call.exception;

public class ExtensionNotFoundException extends RuntimeException {
  public ExtensionNotFoundException(String message) {
    super(message);
  }
}