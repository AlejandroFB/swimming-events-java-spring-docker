package com.zwemmen.psv.api.security.authentication.swimmer;

import com.zwemmen.psv.api.security.authorization.UserRole;
import com.zwemmen.psv.swimmer.BaseSwimmerService;
import com.zwemmen.psv.swimmer.Swimmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Implementation of Spring Security's UserDetailsService to login with the swimmer entity.
 *
 * @author afernandez
 */
@Service
public class SwimmerUserService implements UserDetailsService {

    @Autowired
    private BaseSwimmerService swimmerService;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public SwimmerUser loadUserByUsername(String username) throws UsernameNotFoundException {
        final Swimmer swimmer = swimmerService.findByUserName(username);

        if (swimmer == null) {
            throw new UsernameNotFoundException("Swimmer not found in the application.");
        }

        final SwimmerUser swimmerUser = new SwimmerUser(username, swimmer.getEncryptedPassword(), swimmer, getAuthorities());
        detailsChecker.check(swimmerUser);

        return swimmerUser;
    }

    private List<GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(UserRole.SWIMMER.getRole()));
    }
}