package com.zwemmen.psv.api.exception;

/**
 * Exception used for validate whether a specific resource exists in the service.
 *
 * @author afernandez
 */
public class ApiResourceNotFoundException extends RuntimeException {

    public ApiResourceNotFoundException() {
        super("Resource not found.");
    }
}