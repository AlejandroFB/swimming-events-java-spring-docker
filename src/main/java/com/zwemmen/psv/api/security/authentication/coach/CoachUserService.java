package com.zwemmen.psv.api.security.authentication.coach;

import com.zwemmen.psv.api.security.authorization.UserRole;
import com.zwemmen.psv.coach.BaseCoachService;
import com.zwemmen.psv.coach.Coach;
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
 * Implementation of Spring Security's UserDetailsService to login with the coach entity.
 *
 * @author afernandez
 */
@Service
public class CoachUserService implements UserDetailsService {

    @Autowired
    private BaseCoachService coachService;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public CoachUser loadUserByUsername(String userName) throws UsernameNotFoundException {
        final Coach coach = coachService.findByEmailAddress(userName);

        if (coach == null) {
            throw new UsernameNotFoundException("Coach not found in the application.");
        }

        final CoachUser coachUser = new CoachUser(userName, coach.getEncryptedPassword(), coach, getAuthorities());
        detailsChecker.check(coachUser);

        return coachUser;
    }

    private List<GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(UserRole.COACH.getRole()));
    }
}