package com.zwemmen.psv.api.security.config;

import com.zwemmen.psv.api.security.token.TokenAuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This filter delegates to TokenAuthenticationService. It applies the successful
 * authentication for coaches to Spring Security’s context holder and then proceed with the request.
 *
 * @author afernandez
 */
public class StatelessCoachAuthenticationFilter extends GenericFilterBean {
    private TokenAuthenticationService tokenAuthenticationService;

    public StatelessCoachAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Authentication authentication = tokenAuthenticationService.getCoachAuthentication(httpRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}