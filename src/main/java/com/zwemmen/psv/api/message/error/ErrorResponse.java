package com.zwemmen.psv.api.message.error;

/**
 * ErrorResponse POJO to return response data to the client.
 *
 * @author afernandez
 */
public class ErrorResponse {
    private int code;
    private Error error;

    public ErrorResponse(int code, Error error) {
        this.code = code;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public Error getError() {
        return error;
    }
}