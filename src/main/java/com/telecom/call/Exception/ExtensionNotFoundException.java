package com.telecom.call.Exception;

public class ExtensionNotFoundException extends RuntimeException {
  public ExtensionNotFoundException(String message) {
    super(message);
  }
}