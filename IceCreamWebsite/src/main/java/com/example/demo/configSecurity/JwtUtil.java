package com.example.demo.configSecurity;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String JWT_SECRET;

    @Value("${security.jwt.token.expire-length:3600000}00")
    private final long JWT_EXPIRATION = 360000000;

    // Generate JWT Token base on user's information
    public String generateTokenForUser(CustomUserDetails userDetails) {
        final String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", authorities)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String generateTokenForCustomer(CustomerDetails customerDetails) {
        final String authorities = customerDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(customerDetails.getUsername())
                .claim("role", authorities)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return false;
    }
}

