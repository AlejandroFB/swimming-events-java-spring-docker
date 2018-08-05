package com.zwemmen.psv.api.security.authentication.swimmer;

import com.zwemmen.psv.swimmer.Swimmer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Custom implementation of the Spring security User object.
 *
 * We can use the swimmer object anywhere in the code thanks to the use of @AuthenticationPrincipal in controllers
 * that automatically resolves the user principal: SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 *
 * @author afernandez
 */
public class SwimmerUser extends User {

    private final Swimmer swimmer;

    public SwimmerUser(String username, String password, Swimmer swimmer, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.swimmer = swimmer;
    }

    public SwimmerUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                       boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Swimmer swimmer) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.swimmer = swimmer;
    }

    public Swimmer getSwimmer() {
        return swimmer;
    }
}