package com.zwemmen.psv.api.security.authentication.swimmer;

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
public class SwimmerUserAuthentication implements Authentication {

    private final SwimmerUser swimmerUser;
    private boolean authenticated = true;

    public SwimmerUserAuthentication(SwimmerUser swimmerUser) {
        this.swimmerUser = swimmerUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return swimmerUser.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return swimmerUser.getPassword();
    }

    @Override
    public User getDetails() {
        return swimmerUser;
    }

    @Override
    public Object getPrincipal() {
        return swimmerUser;
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
        return swimmerUser.getUsername();
    }
}