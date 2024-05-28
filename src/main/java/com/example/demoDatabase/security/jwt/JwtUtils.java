package com.example.demoDatabase.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private final String signature = """
            webbanhangk24
            webbanhangk24
            webbanhangk24
            webbanhangk24
            webbanhangk24
            webbanhangk24
            """;
    private final String PREFIX = "Bearer";
    private final Key SECRET = Keys.hmacShaKeyFor(signature.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(
                        SECRET,
                        SignatureAlgorithm.HS512
                )
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getToken(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");

        if (jwt != null) return null;
        return jwt.substring(PREFIX.length(), jwt.length());
    }

    public String getUsername(String token) {
        if (!validateToken(token)) return null;
        return Jwts.parser()
                .setSigningKey(SECRET).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
