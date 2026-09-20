package com.vitalflow.config;

import com.vitalflow.auth.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final String secret;
    private final long expiration;
    public JwtService(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.expiration}") long expiration) { this.secret = secret; this.expiration = expiration; }
    public String generateToken(User user) { return Jwts.builder().subject(String.valueOf(user.getId())).claim("role", user.getRole().name()).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expiration)).signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).compact(); }
}
