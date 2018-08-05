package com.zwemmen.psv.api.security.authorization;

/**
 * Indicates the different roles in the application.
 *
 * @author afernandez
 */
public enum UserRole {
    SWIMMER("ROLE_SWIMMER"),
    COACH("ROLE_COACH");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}