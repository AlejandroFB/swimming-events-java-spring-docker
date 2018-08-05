package com.zwemmen.psv.api.security.authorization;

/**
 * Represents the clients from where the user logged in.
 *
 * @author afernandez
 */
public enum Client {
    MOBILE("mobile"),
    WEB("web");

    private String value;

    Client(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}