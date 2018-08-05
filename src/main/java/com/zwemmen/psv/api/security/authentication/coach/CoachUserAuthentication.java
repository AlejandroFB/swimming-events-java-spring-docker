package com.zwemmen.psv.api.security.authentication.coach;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * This class implements Spring Security’s Authentication interface, which is how Spring
 * ties users, authorities/roles, principals, credentials, and authentication status together.
 *
 * Represents the authenticated principal once the request has been processed by the authentication manager, and it is
 * the one stored by Spring in a thread local and accessed using SecurityContextHolder.getContext().getAuthentication();
 *
 * @author afernandez
 */
public class CoachUserAuthentication implements Authentication {

    private final CoachUser coachUser;
    private boolean authenticated = true;

    public CoachUserAuthentication(CoachUser coachUser) {
        this.coachUser = coachUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return coachUser.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return coachUser.getPassword();
    }

    @Override
    public User getDetails() {
        return coachUser;
    }

    @Override
    public Object getPrincipal() {
        return coachUser;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return coachUser.getUsername();
    }
}