package com.ailtonluiz.bookmanager.core.util;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "9*K_MD4&*3Bg%!s6xzN#1Mu7YYK@w^U~it&R3A";

    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username) {
        long now = System.currentTimeMillis();
        long expirationTime = now + 1000 * 60 * 60 * 10;

        return Jwts.builder()
                .header().type("JWT").and()
                .claim("sub", username)
                .claim("iat", new Date(now))
                .claim("exp", new Date(expirationTime))
                .signWith(SECRET_KEY)
                .compact();
    }
}