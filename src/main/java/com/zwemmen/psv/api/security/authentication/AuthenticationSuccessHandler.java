package com.zwemmen.psv.api.security.authentication;

import com.zwemmen.psv.api.message.AuthenticationSuccessResponse;
import com.zwemmen.psv.api.security.token.TokenAuthenticationService;
import com.zwemmen.psv.api.security.writer.MessageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The login success handler returns http status 200 with user info in json format.
 *
 * @author afernandez
 */
@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String userName = request.getParameter("username");

        final String token = tokenAuthenticationService.generateToken(userName);
        MessageWriter.sendResponse(response, HttpStatus.OK.value(), new AuthenticationSuccessResponse(userName, token));
    }
}