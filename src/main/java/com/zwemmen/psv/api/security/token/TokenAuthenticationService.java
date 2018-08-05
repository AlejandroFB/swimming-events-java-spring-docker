package com.zwemmen.psv.api.security.token;

import com.zwemmen.psv.api.security.authentication.coach.CoachUser;
import com.zwemmen.psv.api.security.authentication.coach.CoachUserAuthentication;
import com.zwemmen.psv.api.security.authentication.coach.CoachUserService;
import com.zwemmen.psv.api.security.authentication.swimmer.SwimmerUser;
import com.zwemmen.psv.api.security.authentication.swimmer.SwimmerUserAuthentication;
import com.zwemmen.psv.api.security.authentication.swimmer.SwimmerUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * When a user successfully logs into the web application, the first public method of this class will be called to
 * create a token for that user.
 *
 * @author afernandez
 */
@Service
public class TokenAuthenticationService {

    @Autowired
    private TokenHandler tokenHandler;
    @Autowired
    private SwimmerUserService swimmerUserService;
    @Autowired
    private CoachUserService coachUserService;

    public String generateToken(String userName) {
        return tokenHandler.createTokenForUser(userName);
    }

    public Authentication getUserAuthentication(HttpServletRequest request) {
        String userName = getUsernameFromToken(request);

        if (StringUtils.isNotEmpty(userName)) {
            return new SwimmerUserAuthentication(retrieveSwimmerUser(userName));
        }
        return null;
    }

    public Authentication getCoachAuthentication(HttpServletRequest request) {
        String userName = getUsernameFromToken(request);

        if (StringUtils.isNotEmpty(userName)) {
            return new CoachUserAuthentication(retrieveCoachUser(userName));
        }
        return null;
    }

    private String getUsernameFromToken(HttpServletRequest request) {
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.isNotEmpty(token) && token.startsWith("Bearer")) {
            return tokenHandler.parseUserFromToken(token.split(" ")[1]);
        }
        return null;
    }

    private SwimmerUser retrieveSwimmerUser(String userName) {
        return swimmerUserService.loadUserByUsername(userName);
    }

    private CoachUser retrieveCoachUser(String userName) {
        return coachUserService.loadUserByUsername(userName);
    }
}