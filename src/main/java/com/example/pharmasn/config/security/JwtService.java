package com.example.pharmasn.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "your-long-secure-secret-key-your-long-secure-secret-key";

    private static final long EXPIRATION_TIME =
            1000 * 60 * 60 * 24;

    private final SecretKey signingKey =
            Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token,Function<Claims, T> resolver
    ) {
        Claims claims = Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return resolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(signingKey)
                .compact();
    }

    public boolean isTokenValid(String token,UserDetails userDetails
    ) {
        String username = extractUsername(token);

        Date expiration =
                extractClaim(token, Claims::getExpiration);

        return username.equals(userDetails.getUsername())
                && expiration.after(new Date());
    }
}