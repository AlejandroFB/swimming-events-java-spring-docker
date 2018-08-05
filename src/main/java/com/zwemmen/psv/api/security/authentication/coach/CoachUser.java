package com.zwemmen.psv.api.security.authentication.coach;

import com.zwemmen.psv.coach.Coach;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Custom implementation of the Spring security User object.
 *
 * We can use the coach object anywhere in the code thanks to the use of @AuthenticationPrincipal in controllers
 * that automatically resolves the user principal: SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 *
 * @author afernandez
 */
public class CoachUser extends User {

    private final Coach coach;

    public CoachUser(String username, String password, Coach coach, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.coach = coach;
    }

    public CoachUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                       boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Coach coach) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.coach = coach;
    }

    public Coach getCoach() {
        return coach;
    }
}