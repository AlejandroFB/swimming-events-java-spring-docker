package com.zwemmen.psv.api.security.config;

import com.zwemmen.psv.api.security.writer.MessageWriter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom AuthenticationEntryPoint to avoid default Spring Security redirecting to login page.
 *
 * In our case it's needed just a http status 401 and a json response to work as a full REST API, as the client
 * has to control the web or application flow.
 *
 * @author afernandez
 */
@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    private static final String AUTH_FAILED = "Authentication failed";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
        MessageWriter.sendError(response, ex, HttpStatus.UNAUTHORIZED.value(), AUTH_FAILED);
    }
}