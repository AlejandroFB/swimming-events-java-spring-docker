package com.zwemmen.psv.api.message;

/**
 * Information added to the response sent after a successful authentication. Used to include the JWT token.
 *
 * @author afernandez
 */
public class AuthenticationSuccessResponse {
    private String userName;
    private String token;

    public AuthenticationSuccessResponse(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
    }
}