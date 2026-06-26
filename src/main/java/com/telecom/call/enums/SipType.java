package com.telecom.call.enums;

public enum SipType {
    USER("user"),
    PEER("peer"),
    FRIEND("friend");

    private final String value;

    SipType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
