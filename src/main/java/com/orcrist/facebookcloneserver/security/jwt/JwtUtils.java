package com.orcrist.facebookcloneserver.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.orcrist.facebookcloneserver.security.service.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.time.ZonedDateTime;

@Component
public class JwtUtils {

    @Value("${spring.security.jwt.secret}")
    private String jwtSecret;

    public String generateToken(Authentication auth) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());
        UserDetailsImpl principals = (UserDetailsImpl) auth.getPrincipal();
        return JWT.create()
                .withSubject("user details")
                .withClaim("id", principals.getId())
                .withClaim("username", principals.getUsername())
                .withIssuedAt(new Date())
                .withIssuer("Facebook-clone")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public String validateToken(String token) throws JWTVerificationException {
        JWTVerifier jwt = JWT.require(Algorithm.HMAC256(jwtSecret))
                .withSubject("user details")
                .withIssuer("Facebook-clone")
                .build();
        return jwt.verify(token).getClaim("username").asString();
    }
}
