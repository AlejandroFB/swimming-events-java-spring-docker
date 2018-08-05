package com.zwemmen.psv.api.message.error;

/**
 * Error POJO used to include error information in a response to the client.
 *
 * @author afernandez
 */
public class Error {
    private String reason;
    private String message;

    public Error(String reason, String message) {
        this.reason = reason;
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public String getMessage() {
        return message;
    }
}