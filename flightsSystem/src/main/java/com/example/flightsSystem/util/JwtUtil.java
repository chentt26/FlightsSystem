package com.example.flightsSystem.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class JwtUtil {

    ArrayList<String> tokens = new ArrayList<>();

    public JwtUtil() {

    }


    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    private String encodedSecretKey = "this+is+my+key+and+it+must+be+at+least+256+bits+long";
    private Key decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey),
            this.signatureAlgorithm);

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userDetails.id);
        claims.put("userRole", userDetails.role);
        String token = createToken(claims, userDetails.username);
        tokens.add(token);
        return token;
    }

    private String createToken(Map<String, Object> claims, String subject) {

        Instant now = Instant.now();

        return Jwts.builder().setClaims(claims)

                .setSubject(subject)

                .setIssuedAt(Date.from(now))

                .setExpiration(Date.from(now.plus(10, ChronoUnit.HOURS)))

                .signWith(this.decodedSecretKey)

                .compact();
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(this.decodedSecretKey).build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    /**
     * returns the JWT subject - in our case the email address
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {

        return extractAllClaims(token).getExpiration();

    }

    public int extractId(String token) {
        return (int) extractAllClaims(token).get("userId");
    }

    public boolean isTokenExpired(String token) {
        try {
            extractAllClaims(token);
            return false;
        } catch (ExpiredJwtException e) {
            tokens.remove(token);
            return true;
        }
    }

    /**
     * returns true if the user (email) in the specified token equals the one in the
     * specified user details and the token is not expired
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.username) && !isTokenExpired(token));
    }

    public static class UserDetails {
        public int id;
        public String username;
        public String role;

        public UserDetails(int id, String username, String role) {
            super();
            this.id = id;
            this.username = username;
            this.role = role;
        }

    }

    public boolean checkIfTokenExists(String jwtToken) {
        String token = tokens.stream().filter(t -> t.equalsIgnoreCase(jwtToken)).findFirst().orElse(null);
        if (token == null || isTokenExpired(token))
            return false;
        else
            return true;
    }

    public String getTokenByUsername(String username) {
        for (int i = 0; i < tokens.size(); i++) {
            if (extractUsername(tokens.get(i)).equals(username))
                return tokens.get(i);
        }
        return null;
    }
}


