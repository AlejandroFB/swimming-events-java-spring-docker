package com.zwemmen.psv.api.security.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * This is where the magic of JSON Web Tokens happens.
 *
 * @author afernandez
 */
@Component
public final class TokenHandler {

    @Value("${security.token.secret}")
    private String secret;

    @PostConstruct
    public void init() {
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String parseUserFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String createTokenForUser(String userName) {
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(userName)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}