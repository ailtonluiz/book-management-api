package com.ailtonluiz.bookmanager.core.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
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



    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // O nome de usuário está no campo "sub"
    }


    public boolean validateToken(String token, String username) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);

            String extractedUsername = claims.getBody().getSubject();
            Date expiration = claims.getBody().getExpiration();

            return username.equals(extractedUsername) && !expiration.before(new Date());
        } catch (JwtException e) {
            return false; // Token inválido
        }
    }
}